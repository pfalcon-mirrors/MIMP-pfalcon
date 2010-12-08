package org.mimp.parser;

import org.xml.sax.AttributeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author hellhand
 */
@SuppressWarnings("unused")
public abstract class GPXHandlerImplLight implements GPXHandler {

    private boolean inWPTInfos;
    private boolean inGPXMedia;
    private boolean inExtensions;
    private boolean inPublisher;
    private boolean inAuthor;
    private boolean inTRKseg;
    private boolean inGPXInfos;
    private boolean inGPX;
    private boolean inMetadata;
    private boolean inTrkpt;
    private boolean inTrkInfos;
    private boolean inRTE;
    private boolean inTRK;
    private boolean inCopyright;
    private boolean inRTEInfos;
    private boolean inRTEpt;
    private boolean inWPT;
    private boolean inSource;
    private boolean inMedia;

    GPXObject gpxa;
    PointOfInterest poi;
    MediaPoint media;
    GPXRoute route;
    GPXTrack track;
    GeoPointer pointer;

    public void start_metadata(final AttributeList meta) throws SAXException {
        inMetadata = true;
    }

    public void end_metadata() throws SAXException {
        inMetadata = false;
    }

    public void handle_name(final String data, final AttributeList meta)
            throws SAXException {
        if (inMetadata) {
            if (inAuthor) {
                gpxa.setAuthor(data);
            }
            else if (inGPXInfos) {
                gpxa.addGpxInfoName(meta.getValue("lang"), data);
            }
        }
    }

    public void handle_desc(final String data, final AttributeList meta)
            throws SAXException {
        if (inMetadata) {
            if (inGPXInfos) {
                if (meta.getValue("lang") == null) {
                    gpxa.addGpxInfoDescr("all", "");
                }
                else {
                    gpxa.addGpxInfoDescr(meta.getValue("lang"), data);
                }
            }
        }
    }

    public void start_author(final AttributeList meta) throws SAXException {
        inAuthor = true;
    }

    public void end_author() throws SAXException {
        inAuthor = false;
    }

    public void start_copyright(final AttributeList meta) throws SAXException {
        inCopyright = true;
    }

    public void end_copyright() throws SAXException {
        inCopyright = false;
    }

    public void handle_bounds(final AttributeList meta) throws SAXException {
        float minlat, minlon, maxlat, maxlon = 0;
        if (meta.getValue("minlat") == null) {
            minlat = 0;
        }
        else {
            minlat = Float.parseFloat(meta.getValue("minlat"));
        }
        if (meta.getValue("maxlat") == null) {
            maxlat = 0;
        }
        else {
            maxlat = Float.parseFloat(meta.getValue("maxlat"));
        }
        if (meta.getValue("minlon") == null) {
            minlon = 0;
        }
        else {
            minlon = Float.parseFloat(meta.getValue("minlon"));
        }
        if (meta.getValue("maxlon") == null) {
            maxlon = 0;
        }
        else {
            maxlon = Float.parseFloat(meta.getValue("maxlon"));
        }
        gpxa.setLatLon(minlat, maxlat, minlon, maxlon);
    }

    public void start_publisher(final AttributeList meta) throws SAXException {
        inPublisher = true;
    }

    public void end_publisher() throws SAXException {
        inPublisher = false;
    }

    public void handle_year(final String data, final AttributeList meta)
            throws SAXException {
        if (inCopyright) {
            if (data == null) {
                gpxa.setYear("");
            }
            else {
                gpxa.setYear(data);
            }
        }
    }

    public void start_gpx(final AttributeList meta) throws SAXException {
        inGPX = true;
        gpxa = new GPXObject();
    }

    public void end_gpx() throws SAXException {
        inGPX = false;
    }

    public void start_trkinfos(final AttributeList meta) throws SAXException {
        inTrkInfos = true;
    }

    public void end_trkinfos() throws SAXException {
        inTrkInfos = false;
    }

    public void handle_email(final AttributeList meta) throws SAXException {
        if (inAuthor) {
            gpxa.setEmail(meta.getValue("id") + "@" + meta.getValue("domain"));
        }
    }

    public void end_content() throws SAXException {

    }

    public GPXObject getGPXObject() {
        return gpxa;
    }
}
