/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mimp.search;

import java.util.List;

import org.mapping.google.impl.Locator;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.location.Address;
import android.net.Uri;

public class LocationSearchProvider extends ContentProvider {
        
    private static final String[] COLUMNS = {
        "_id",
        SearchManager.SUGGEST_COLUMN_TEXT_1,
        SearchManager.SUGGEST_COLUMN_INTENT_DATA,
        };
    
    
    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        MatrixCursor cur = new MatrixCursor(COLUMNS);
        List<Address> list = Locator.getLocations(getContext(), selectionArgs[0], 15);
        for (int i=0; i < list.size() ;i++) {
            cur.addRow(columnValuesOfWord(i, list.get(i)));
        }
        return cur;
    }
    
    
    private Object[] columnValuesOfWord(long id, Address address) {
        if (address.getAddressLine(1) == null) {
            return new Object[] {
                id,
                address.getAddressLine(0),
                new double[] {
                    address.getLatitude(),
                    address.getLongitude(),   
                },
             };
        }
        else {
            return new Object[] {
                id,
                address.getAddressLine(0) + " " + address.getAddressLine(1),
                new double[] {
                    address.getLatitude(),
                    address.getLongitude(),
                },
             };
        }
    }

    public String getType(Uri uri) {
        throw new IllegalArgumentException("Unknown URL " + uri);
    }
    
    
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }
}
