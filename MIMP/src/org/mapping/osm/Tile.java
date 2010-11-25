package org.mapping.osm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Tile {
    public int x;
    public int y;
    public int z;
    boolean photo;
    public Bitmap bitmap;
    public int state;
    byte[] dat = null;

    public Tile(boolean isPhoto, int tx, int ty, int tz) {
        photo = isPhoto;
        x = tx;
        y = ty;
        z = tz;
        bitmap = null;
        state = 0;
    }

    public void load() {
        dat = OsmApi.tileOpen(x, y, z);
        if (dat == null || dat.length == 0) {
            dat = OsmApi.tileLoad(x, y, z);
        }
        bitmap = BitmapFactory.decodeByteArray(dat, 0, dat.length);
        state = 1;
    }

    public void save() {
        byte[] dat = null;
        dat = OsmApi.tileOpen(x, y, z);
        if (dat == null || dat.length == 0) {
            OsmApi.tileSave(this.dat, x, y, z);
        }
    }

    public void dispose() {
        bitmap = null;
        state = 0;
    }
}