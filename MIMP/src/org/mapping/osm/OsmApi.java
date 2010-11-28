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
    static final double _r2d = 180.0 / Math.PI;
    static final double _ky = 6378137.0 * _d2r;
    static final double _kx = _ky * Math.cos(46.5 * _d2r);
    static int zmax = 18;

    /*****************************************************************************
     * 
     * Math functions
     * 
     *****************************************************************************/
    
    public static int[] LatLngToPixel(double[] latlng, int zoom) {
    	//TODO: some math
    	return new int[] { 0, 0 };
    }

    public static double[] PixelToLatLng(int[] pixel, int zoom) {
    	//TODO: some math
        return new double[] { 0, 0 };
    }

    /*****************************************************************************
     * 
     * Download functions
     * 
     *****************************************************************************/
    
    public static byte[] tileLoad(int tx, int ty, int z) {
        /**
         *  http://tile.openstreetmap.org/7/63/42.png
         */
        String url = "http://tile.openstreetmap.org/" + (zmax-z) + "/" + tx + "/" + ty + ".png";
        byte[] dat = download(url);
        return dat;
    }
    
    private static byte[] download(String url) {
        byte[] dat = null;
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0");
            urlConnection.connect();
            dat = NetUtil.download(urlConnection);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return dat;
    }
    
    /*****************************************************************************
     * 
     * Opening functions
     * 
     *****************************************************************************/
    
    public static byte[] tileOpen(int tx, int ty, int z) {
    	//TODO: generate filename
    	String filename = "";
        byte[] dat = open(filename);
        return dat;
    }
    
    /**
     * 
     * @param filename
     * @return dat : tile in byte array
     */
    private static byte[] open(String filename) {
        Log.i("OsmApi Open" , filename);
    	byte[] dat = null;
        long length = 0;
        try {
            File file = Environment.getExternalStorageDirectory();
            file = new File(file.getAbsolutePath() + File.separator + "Tiles" + File.separator + "OSM");
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
        return dat;
    }

    /*****************************************************************************
     * 
     * Saving functions
     * 
     *****************************************************************************/
    
    /**
     * 
     * @param dat tile in byte array
     * @param tx 
     * @param ty
     * @param z
     */
    public static void tileSave(byte[] dat, int tx, int ty, int z) {
        String filename = tx + "_" + ty + "_" + (zmax-z);
        save(filename, dat);
    }
    
    /**
     * 
     * @param filename
     * @param dat : tile in byte array
     */
    private static void save(String filename, byte[] dat) {
        Log.i("OsmApi Save" , filename);
        if (dat == null)
            return;
        File file = Environment.getExternalStorageDirectory();
        file = new File(file.getAbsolutePath() + File.separator + "Tiles" + File.separator + "OSM");
        file.mkdirs();
        file = new File(file.getAbsolutePath() + File.separator + filename);
        if (!file.exists()) {
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(dat);
                fileOutputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
