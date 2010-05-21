package org.mapping.osm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.os.Environment;
import android.util.Log;

public class OsmApi {
    static final double _d2r = Math.PI / 180;
    static final double _ky = 6378137.0 * _d2r;
    static final double _kx = _ky * Math.cos(46.5 * _d2r);
    static int zmax = 18;

    public static int[] LatLngToPixel(double[] latlng, int zoom) {
        double mx = latlng[1] * _kx;
        double my = latlng[0] * _ky;
        double mpp = (1 << zmax-zoom) / 256.0;
        int px = (int) (mx / mpp + 0.5);
        int py = (int) (my / mpp + 0.5);
        return new int[] { px, py };
    }

    public static double[] PixelToLatLng(int[] pixel, int zoom) {
        double mpp = (1 << zmax-zoom) / 256.0;
        double lat = pixel[1] * mpp / _ky;
        double lon = pixel[0] * mpp / _kx;
        return new double[] { lat, lon };
    }

    static byte[] download(String url) {
        byte[] dat = null;
        try {
            URLConnection cnx = new URL(url).openConnection();
            cnx.addRequestProperty("User-Agent", "Mozilla/5.0");
            cnx.connect();
            dat = NetUtil.download(cnx);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return dat;
    }

    public static byte[] open(String filename) {
        byte[] dat = null;
        long length = 0;
        Log.e("OsmApi Open" , filename);
        try {
            File file = Environment.getExternalStorageDirectory();
            file = new File(file.getAbsolutePath() + File.separator + "osm" + File.separator + "cache");
            file.mkdirs();
            file = new File(file.getAbsolutePath() + File.separator + filename);
            FileInputStream fileInputStream;
            fileInputStream = new FileInputStream(file);
            
            length = file.length(); 
            dat = new byte[(int)length];
            
            fileInputStream.read(dat);
            fileInputStream.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("OsmApi Open" , filename + " " + length + " " + dat);
        return dat;
    }

    public static void save(String filename, byte[] dat) {
        if (dat == null)
            return;
        File file = Environment.getExternalStorageDirectory();
        file = new File(file.getAbsolutePath() + File.separator + "osm" + File.separator + "cache");
        file.mkdirs();
        file = new File(file.getAbsolutePath() + File.separator + filename);
        if (!file.exists()) {
            Log.e("OsmApi Save" , ""+dat);
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(file);
                Log.e("OsmApi" , ""+fileOutputStream);
                fileOutputStream.write(dat);
                fileOutputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] tileLoad(int tx, int ty, int z) {
        // http://tile.openstreetmap.org/7/63/42.png
        String url = "http://tile.openstreetmap.org/" + (zmax-z) + "/" + tx + "/" + ty + ".png";
        Log.e("OsmApi DL" , url.toString());
        byte[] dat = download(url);
        return dat;
    }

    public static void tileSave(byte[] dat, int tx, int ty, int z) {
        String filename = tx + "_" + ty + "_" + (zmax-z);
        save(filename, dat);
    }

    public static byte[] tileOpen(int tx, int ty, int z) {
        String filename = tx + "_" + ty + "_" + (zmax-z);
        byte[] dat = open(filename);
        return dat;
    }
}
