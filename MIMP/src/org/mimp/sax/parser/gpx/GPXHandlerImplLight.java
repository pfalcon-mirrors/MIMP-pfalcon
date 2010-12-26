package org.mimp.sax.parser.gpx;

import org.mimp.sax.parser.GeoPointer;
import org.mimp.sax.parser.MediaPoint;
import org.mimp.sax.parser.PointOfInterest;
import org.xml.sax.AttributeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author hellhand
 */
@SuppressWarnings("unused")
public class GPXHandlerImplLight implements GPXHandler {

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

    GPXObject gpxa = new GPXObject();
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
        if (inTRK) {
            gpxa.setName(data);
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
        if (inTRK) {
            gpxa.setDescr(data);
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
                gpxa.setDate("");
            }
            else {
                gpxa.setDate(data);
            }
        }
    }

    public void start_gpx(final AttributeList meta) throws SAXException {
        inGPX = true;
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

    public GPXObject getGPXObject() {
        return gpxa;
    }

    /**
     * NO IMPLEMENTATION NEEDED 
     */
    
    @Override
    public void start_wptinfos(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_wptinfos() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_elemin(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_gpxmedia(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_gpxmedia() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_sym(String data, AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_type(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_extensions(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_extensions() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_distance(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_time(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_title(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_trkseg(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_trkseg() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_activitytype(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_gpxinfos(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_gpxinfos() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_gpx() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_realdistance(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_ele(String data, AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_thl(String data, AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_trkpt(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_trkpt() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_thg(String data, AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_trk(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_trk() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_rte(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_rte() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_url(String data, AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_rteinfos(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_rteinfos() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_duration(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_rtept(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_rtept() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_wpt(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_wpt() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_source(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_source() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_medias(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_medias() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_bearing(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_radius(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_characteristic(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_maxspeed(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_media(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_media() throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_elemax(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void start_content(AttributeList meta) throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handle_content(String data, AttributeList meta)
            throws SAXException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end_content() throws SAXException {
        // TODO Auto-generated method stub
        
    }
}
