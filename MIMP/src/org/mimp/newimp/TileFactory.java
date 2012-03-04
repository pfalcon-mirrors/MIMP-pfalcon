package org.mimp.newimp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.mimp.globals.S;

import android.os.Environment;
import android.util.Log;

public class TileFactory {

    public static int TILE_SIZE = 256;
    public static final double MAX_LAT = 85.05112877980659;
    public static final double MIN_LAT = -85.05112877980659;
    private static File mExtFolder = Environment.getExternalStorageDirectory();

    /*****************************************************************************
     * 
     * Math functions
     * 
     *****************************************************************************/

    public static double radius(int aZoomlevel) {
        return (TILE_SIZE * (1 << aZoomlevel)) / (2.0 * Math.PI);
    }

    public static int getMaxPixels(int aZoomlevel) {
        return TILE_SIZE * (1 << aZoomlevel);
    }

    public static int falseNorthing(int aZoomlevel) {
        return (-1 * getMaxPixels(aZoomlevel) / 2);
    }

    public static double[] PixelToLatLng(int[] xy, int zoom) {

        double lat = xy[1] + falseNorthing(zoom);
        lat = (Math.PI / 2)
                - (2 * Math.atan(Math.exp(-1.0 * lat / radius(zoom))));
        lat = -1 * Math.toDegrees(lat);

        double lon = ((360d * xy[0]) / (TILE_SIZE * (1 << zoom))) - 180.0;

        if (lat > MAX_LAT)
            lat = MAX_LAT;
        else if (lat < MIN_LAT)
            lat = MIN_LAT;

        if (lon > 180)
            lon = 180;
        else if (lon < -180)
            lon = -180;

        return new double[] { lat, lon };
    }

    public static int[] LatLngToPixel(double[] latlon, int zoom) {
        int x = (int) ((TILE_SIZE * (1 << zoom) * (latlon[1] + 180l)) / 360l);
        x = Math.min(x, TILE_SIZE * (1 << zoom) - 1);

        if (latlon[0] < MIN_LAT)
            latlon[0] = MIN_LAT;
        else if (latlon[0] > MAX_LAT)
            latlon[0] = MAX_LAT;

        double sinLat = Math.sin(Math.toRadians(latlon[0]));
        double log = Math.log((1.0 + sinLat) / (1.0 - sinLat));

        int y = (int) (TILE_SIZE * (1 << zoom) * (0.5 - (log / (4.0 * Math.PI))));
        y = Math.min(y, TILE_SIZE * (1 << zoom) - 1);

        return new int[] { x, y };
    }

    /*****************************************************************************
     * 
     * Download functions
     * 
     *****************************************************************************/

    public static byte[] DownloadTile(MapProvider provider, int tx, int ty, int z) {
        String url = provider.getUrl(tx, ty, z);
        if (url == null) {
            return null;
        }
        byte[] dat = null;
        Log.d("TileFactory", "Downloading " + url);
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0");
            urlConnection.setConnectTimeout(500);
            urlConnection.connect();
            dat = WebTools.download(urlConnection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dat;
    }

    /*****************************************************************************
     * 
     * Opening functions
     * 
     *****************************************************************************/

    public static byte[] OpenTile(MapProvider provider, int tx, int ty, int z) {
        if (!provider.isCacheable()) {
            return null;
        }
        String filename = z + "-" + tx + "-" + ty;
        byte[] dat = null;
        String MAPFOLDER = provider.getShortName();
        File file = new File(mExtFolder.getAbsolutePath() + File.separator
                + "MIMP" + File.separator + "Tiles" + File.separator
                + MAPFOLDER + File.separator);
        file.mkdirs();
        file = new File(file.getAbsolutePath() + File.separator + filename);
        /**
         * Saves a useless exception
         */
        if (file.exists() == false)
            return dat;
        long length = 0;
        try {
            FileInputStream fileInputStream;
            fileInputStream = new FileInputStream(file);
            length = file.length();
            dat = new byte[(int) length];
            fileInputStream.read(dat);
            fileInputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return dat;
    }

    /*****************************************************************************
     * 
     * Saving functions
     * 
     *****************************************************************************/

    public static void SaveTile(MapProvider provider, byte[] dat, int tx, int ty, int z) {
        if (!provider.isCacheable()) {
            return;
        }
        String filename = z + "-" + tx + "-" + ty;
        String MAPFOLDER = provider.getShortName();
        File file = new File(mExtFolder.getAbsolutePath() + File.separator
                + "MIMP" + File.separator + "Tiles" + File.separator
                + MAPFOLDER + File.separator);
        file.mkdirs();
        file = new File(file.getAbsolutePath() + File.separator + filename);
        if (!file.exists()) {
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(dat);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
