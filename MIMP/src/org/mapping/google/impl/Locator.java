package org.mapping.google.impl;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.mimp.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.location.Address;
import android.location.Geocoder;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class Locator {

    private List<Address> mAddresses = null;
    private Context mContext;
    private MapView mMapView;
    private String[] mArray;
    private EditText mSearchBox;

    public Locator(Context context, MapView mapView) {
        this.mContext = context;
        this.mMapView = mapView;
    }

    public void proposeSearch() {
        mSearchBox = new EditText(mContext);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.searchbox_title)
                .setCancelable(false)
                .setView(mSearchBox)
                .setPositiveButton(android.R.string.search_go,
                        new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                proposeLocations(mSearchBox.getText()
                                        .toString());
                                mSearchBox = null;
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                dialog.dismiss();
                                mSearchBox = null;
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void proposeLocations(String location) {
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            mAddresses = geocoder.getFromLocationName(location, 100);
        }
        catch (IOException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage(e.getMessage()).setNeutralButton(
                    android.R.string.ok, new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        if (mAddresses != null) {
            String tempAdress;
            mArray = new String[mAddresses.size()];
            for (int i = 0; i < mAddresses.size(); i++) {
                tempAdress = "";
                if (mAddresses.get(i).getThoroughfare() != null)
                    tempAdress = tempAdress
                            + mAddresses.get(i).getThoroughfare() + " ";
                if (mAddresses.get(i).getCountryName() != null)
                    tempAdress = tempAdress
                            + mAddresses.get(i).getCountryName() + " ";
                if (mAddresses.get(i).getLocality() != null)
                    tempAdress = tempAdress + mAddresses.get(i).getLocality();
                mArray[i] = tempAdress;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            ListAdapter listAdapter = new ArrayAdapter<String>(mContext,
                    android.R.layout.simple_list_item_single_choice, mArray);
            builder.setTitle("Search : ")
                    .setCancelable(false)
                    .setSingleChoiceItems(listAdapter, -1,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int item) {
                                    displayLocation(mAddresses, item);
                                    dialog.dismiss();
                                }
                            })
                    .setNegativeButton("Cancel", new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private void displayLocation(List<Address> addresses, int arg2) {
        Address address = addresses.get(arg2);
        Double lat = address.getLatitude() * 1E6;
        Double lng = address.getLongitude() * 1E6;

        GeoPoint point = new GeoPoint(lat.intValue(), lng.intValue());
        mMapView.getController().animateTo(point);
    }
}
