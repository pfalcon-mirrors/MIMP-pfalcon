package org.mimp.dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.mimp.dom.gpx.GpxType;
import org.mimp.dom.gpx.scanner.GpxScanner;
import org.mimp.dom.gpx.scanner.GpxScannerLight;
import org.mimp.dom.kml.KmlType;
import org.w3c.dom.Document;

public class ParsedFileFactory {
    
    private static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder dBuilder = null;
    private static Document doc = null;
    
    public static ParsedFile getParsedFile(File file) {
        ParsedFile mParsedFile = null;
        String mPath = file.getAbsolutePath();
        int dot = mPath.lastIndexOf(".");
        String mExtention = mPath.substring(dot + 1);        
        try {
            if (mExtention.equalsIgnoreCase("gpx")) {
                if (dBuilder == null)
                    dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.parse(file);
                GpxScanner scanner = new GpxScanner(doc);
                scanner.visitDocument();
                GpxType gpxType = (GpxType) scanner.getParsedObject();
                mParsedFile = (ParsedFile) new GPXFile(file.getAbsolutePath(), gpxType);
            }
            else if (mExtention.equalsIgnoreCase("kml")) {
                /*
                if (dBuilder == null)
                    dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.parse(file);
                KmlScanner scanner = new KmlScanner(doc);
                scanner.visitDocument();
                KmlType kmlType = (KmlType) scanner.getParsedObject();
                 */
                KmlType kmlType = new KmlType();
                mParsedFile = (ParsedFile) new KMLFile(file.getAbsolutePath(), kmlType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mParsedFile;
    }
    
    public static ParsedFile getLightParsedFile(File file) {
        ParsedFile mParsedFile = null;
        String mPath = file.getAbsolutePath();
        int dot = mPath.lastIndexOf(".");
        String mExtention = mPath.substring(dot + 1);        
        try {
            if (mExtention.equalsIgnoreCase("gpx")) {
                if (dBuilder == null)
                    dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.parse(file);
                GpxScannerLight scanner = new GpxScannerLight(doc);
                scanner.visitDocument();
                GpxType gpxType = (GpxType) scanner.getParsedObject();
                mParsedFile = (ParsedFile) new GPXFile(file.getAbsolutePath(), gpxType);
            }
            else if (mExtention.equalsIgnoreCase("kml")) {
                /*
                if (dBuilder == null)
                    dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.parse(file);
                KmlScannerLight scanner = new KmlScannerLight(doc);
                scanner.visitDocument();
                KmlType kmlType = (KmlType) scanner.getParsedObject();
                 */
                KmlType kmlType = new KmlType();
                mParsedFile = (ParsedFile) new KMLFile(file.getAbsolutePath(), kmlType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mParsedFile;
    }
}
