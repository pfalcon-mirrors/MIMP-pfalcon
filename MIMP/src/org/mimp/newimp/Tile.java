package org.mimp.newimp;

import java.io.File;

import org.mimp.globals.S;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Environment;

public class Tile {

    private static enum STATUS {
        BLANK, USED,
    }

    /**
     * Defining default Tile appearance, this will appear as the MapView
     * background. The Exception will occur if file not found on SD-Card
     */
    private static Bitmap BITMAPBG = null;
    static {
        try {
            File ExtFolder = Environment.getExternalStorageDirectory();
            String BgPath = ExtFolder.getAbsoluteFile() + File.separator
                    +"MIMP" + File.separator + "Tiles" + File.separator + "mbg.png";
            Options BgOptions = new Options();
            BgOptions.inPurgeable = true;
            BITMAPBG = BitmapFactory.decodeFile(BgPath, BgOptions);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int X;
    private int Y;
    private int Z;
    private byte[] mData = null;
    private Bitmap mBitmap = null;
    private STATUS mState = STATUS.BLANK;
    private Context mContext;
    private SharedPreferences mSettings;

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getZ() {
        return Z;
    }

    public void setZ(int z) {
        Z = z;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public Tile(int tx, int ty, int tz, Context context) {
        X = tx;
        Y = ty;
        Z = tz;
        mBitmap = BITMAPBG;
        mContext = context;
        mSettings = mContext.getSharedPreferences(S.PREFS_NAME, 0);
    }

    /**
     * Generates the Tile from a bitmap decoded from a local source or the
     * network
     */
    public void load() {
        if (mState == STATUS.USED)
            return;
        String url = mSettings.getString("map_provider_name", S.OpenCycleMapsURL);
        if (mData == null) {
            mData = TileFactory.OpenTile(url, X, Y, Z);
        }
        if (mData == null || mData.length == 0) {
            mData = TileFactory.DownloadTile(url, X, Y, Z);
        }
        if (mData != null) {
            mBitmap = BitmapFactory.decodeByteArray(mData, 0, mData.length);
            mState = STATUS.USED;
        }
    }

    /**
     * Save the tile on the local source
     */
    public void save() {
        if (mData != null && mData.length != 0) {
            String url = mSettings.getString("map_provider_name", S.OpenCycleMapsURL);
            TileFactory.SaveTile(url, mData, X, Y, Z);
        }
    }

    public void save(String provider) {
        mData = TileFactory.DownloadTile(provider, X, Y, Z);
        if (mData != null && mData.length != 0) {
            TileFactory.SaveTile(provider, mData, X, Y, Z);
        }
    }
    
    /**
     * Flag the Tile to be refreshed
     */
    public void purge() {
        mState = STATUS.BLANK;
    }
}