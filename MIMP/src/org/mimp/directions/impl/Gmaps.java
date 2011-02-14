package org.mimp.directions.impl;

import java.net.URL;
import java.net.URLConnection;

import org.mimp.newimp.NetUtil;

public class Gmaps {
    public static double[] findLocation(String query) {
        double[] aa = null;
        try {
            String enco = java.net.URLEncoder.encode(query, "UTF-8");
            String url = "http://maps.google.com/maps/geo?q=" + enco
                    + "&oe=utf8&output=csv";
            URLConnection cnx = new URL(url).openConnection();
            cnx.setRequestProperty("Referer", "http://maps.google.fr");
            cnx.setRequestProperty("User-Agent", "Mozilla/4.0");
            cnx.connect();
            byte[] dat = NetUtil.download(cnx);
            String ss = new String(dat, "UTF-8");
            String l[] = ss.split("\n");
            if (l.length > 0) {
                String[] s = l[0].split("\"");
                if (s.length == 1) {
                    String[] e = l[0].split(",");
                    int code = Integer.parseInt(e[0]);
                    if (code == 200) {
                        double lat = Double.parseDouble(e[2]);
                        double lon = Double.parseDouble(e[3]);
                        aa = new double[] { lat, lon };
                    }
                }
            }

        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
        return aa;
    }
}
