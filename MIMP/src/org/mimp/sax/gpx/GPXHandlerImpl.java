package org.mimp.sax.gpx;

import org.mimp.sax.GeoPointer;
import org.mimp.sax.MediaPoint;
import org.mimp.sax.PointOfInterest;
import org.xml.sax.AttributeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author hellhand
 */
@SuppressWarnings("unused")
public class GPXHandlerImpl implements GPXHandler {

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
        else if (inWPT) {
            if (inWPTInfos) {
                poi.addName(meta.getValue("lang"), data);
            }
            if (inMedia) {
                if (inSource) {
                    media.setSourceName(data);
                }
                else if (inPublisher) {
                    media.setPubName(data);
                }
                else if (inAuthor) {
                    media.setAuthName(data);
                }
            }
        }
        else if (inRTE) {
            route.addGpxRteInfoName(meta.getValue("name"), data);
        }
        else if (inTRK) {
            gpxa.setName(data);
        }
    }

    public void handle_desc(final String data, final AttributeList meta)
            throws SAXException {
        if (inMetadata) {
            if (inGPXInfos) {
                if (meta.getValue("lang") == null) {
                    if (data == null) {
                        gpxa.addGpxInfoDescr("all", "");
                    }
                    else {
                        gpxa.addGpxInfoDescr("all", data);
                    }
                }
                else {
                    gpxa.addGpxInfoDescr(meta.getValue("lang"), data);
                }
            }
            else {
                if (meta.getValue("lang") == null) {
                    gpxa.setDescr("");
                }
                else {
                    gpxa.setDescr(data);
                }
            }
        }
        else if (inWPT) {
            if (meta.getValue("lang") == null) {
                poi.addDesc("all", "");
            }
            else {
                poi.addDesc(meta.getValue("lang"), data);
            }
        }
        else if (inTRK) {
            gpxa.setDescr(data);
        }
    }

    public void start_author(final AttributeList meta) throws SAXException {
        inAuthor = true;
    }

    public void end_author() throws SAXException {
        inAuthor = false;
    }

    public void start_wptinfos(final AttributeList meta) throws SAXException {
        inWPTInfos = true;
    }

    public void end_wptinfos() throws SAXException {
        inWPTInfos = false;
    }

    public void handle_elemin(final String data, final AttributeList meta)
            throws SAXException {
        if (data != null) {
            track.setEleMin(Integer.parseInt(data));
        }
        else {
            track.setEleMin(0);
        }
    }

    public void handle_elemax(final String data, final AttributeList meta)
            throws SAXException {
        if (data != null) {
            track.setEleMax(Integer.parseInt(data));
        }
        else {
            track.setEleMax(0);
        }
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

    public void start_extensions(final AttributeList meta) throws SAXException {
        inExtensions = true;
    }

    public void end_extensions() throws SAXException {
        inExtensions = false;
    }

    public void start_gpxmedia(final AttributeList meta) throws SAXException {
        inGPXMedia = true;
    }

    public void end_gpxmedia() throws SAXException {
        inGPXMedia = false;
    }

    public void start_gpxinfos(final AttributeList meta) throws SAXException {
        inGPXInfos = true;
    }

    public void end_gpxinfos() throws SAXException {
        inGPXInfos = false;
    }

    public void start_wpt(final AttributeList meta) throws SAXException {
        inWPT = true;
        poi = new PointOfInterest();
        pointer = new GeoPointer(0, 0);
    }

    public void end_wpt() throws SAXException {
        inWPT = false;
        gpxa.addPoint(poi);
    }

    public void handle_sym(final String data, final AttributeList meta)
            throws SAXException {
        if (inWPT) {
            if (inWPTInfos) {
                if (data == null) {
                    poi.setInfosSym("");
                }
                else {
                    poi.setInfosSym(data);
                }
            }
            else {
                if (data == null) {
                    poi.setSym("");
                }
                else {
                    poi.setSym(data);
                }
            }
        }
    }

    public void handle_type(final String data, final AttributeList meta)
            throws SAXException {
        if (inWPT) {
            if (inMedia) {
                if (data == null) {
                    media.setType("");
                }
                else {
                    media.setType(data);
                }
            }
            else {
                if (data == null) {
                    poi.setType("");
                }
                else {
                    poi.setType(data);
                }
            }
        }
    }

    public void start_medias(final AttributeList meta) throws SAXException {
        // inMedia = true;
        // media = new MediaPoint();
    }

    public void end_medias() throws SAXException {
        // inMedia = false;
        // poi.addMedia(media);
    }

    public void start_publisher(final AttributeList meta) throws SAXException {
        inPublisher = true;
    }

    public void end_publisher() throws SAXException {
        inPublisher = false;
    }

    public void handle_distance(final String data, final AttributeList meta)
            throws SAXException {
        if (inTRK) {
            track.setDistance(Integer.parseInt(data));
        }
        else if (inRTE) {
            route.setDistance(Integer.parseInt(data));
        }
    }

    public void handle_time(final String data, final AttributeList meta)
            throws SAXException {
        if (inWPT) {
            if (data == null) {
                pointer.setTime("");
            }
            else {
                pointer.setTime(data);
            }
        }
    }

    public void handle_title(final String data, final AttributeList meta)
            throws SAXException {
        if (data == null) {
            media.setTitle("");
        }
        else {
            media.setTitle(data);
        }
    }

    public void start_trkseg(final AttributeList meta) throws SAXException {
        inTRKseg = true;
    }

    public void end_trkseg() throws SAXException {
        inTRKseg = false;
    }

    public void handle_activitytype(final String data, final AttributeList meta)
            throws SAXException {
        if (inTrkInfos) {
            if (data == null) {
                track.setActivity(0);
            }
            else {
                track.setActivity(Integer.valueOf(data));
            }
        }
        else if (inRTEInfos) {
            if (data == null) {
                route.setActivity(0);
            }
            else {
                route.setActivity(Integer.valueOf(data));
            }
        }
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
        gpxa = new GPXObject();
    }

    public void end_gpx() throws SAXException {
        inGPX = false;
    }

    public void handle_realdistance(final String data, final AttributeList meta)
            throws SAXException {
        if (inTrkInfos) {
            if (data == null) {
                track.setRealdistance(0);
            }
            else {
                track.setRealdistance(Integer.valueOf(data));
            }
        }
    }

    public void handle_ele(final String data, final AttributeList meta)
            throws SAXException {
        if (inTrkpt) {
            if (data == null) {
                pointer.setElevation(0.0f);
            }
            else {
                pointer.setElevation(Float.valueOf(data).floatValue());
            }
        }
        else if (inRTEpt) {
            if (data == null) {
                pointer.setElevation(0.0f);
            }
            else {
                pointer.setElevation(Float.valueOf(data).floatValue());
            }
        }
    }

    public void handle_thl(final String data, final AttributeList meta)
            throws SAXException {
        if (inTrkInfos) {
            if (data == null) {
                track.setThl(0);
            }
            else {
                track.setThl(Integer.valueOf(data));
            }
        }
        else if (inRTEInfos) {
            if (data == null) {
                route.setThl(0);
            }
            else {
                route.setThl(Integer.valueOf(data));
            }
        }
    }

    public void start_trkpt(final AttributeList meta) throws SAXException {
        inTrkpt = true;
        pointer = new GeoPointer(
                (int) (Double.parseDouble(meta.getValue("lat")) * 1E6),
                (int) (Double.parseDouble(meta.getValue("lon")) * 1E6));
    }

    public void end_trkpt() throws SAXException {
        track.addGeoPointer(pointer);
    }

    public void start_trkinfos(final AttributeList meta) throws SAXException {
        inTrkInfos = true;
    }

    public void end_trkinfos() throws SAXException {
        inTrkInfos = false;
    }

    public void handle_thg(final String data, final AttributeList meta)
            throws SAXException {
        if (inTrkInfos) {
            track.setThg(Integer.valueOf(data));
        }
        else if (inRTEInfos) {
            route.setThg(Integer.valueOf(data));
        }
    }

    // TODO : check following nulls
    public void start_trk(final AttributeList meta) throws SAXException {
        inTRK = true;
        track = new GPXTrack();
    }

    public void end_trk() throws SAXException {
        inTRK = false;
        gpxa.setTrack(track);
    }

    public void start_rte(final AttributeList meta) throws SAXException {
        inRTE = true;
        route = new GPXRoute();
    }

    public void end_rte() throws SAXException {
        inRTE = false;
        gpxa.setRoute(route);
    }

    public void handle_url(final String data, final AttributeList meta)
            throws SAXException {
        if (inMedia) {
            if (inSource) {
                media.setSourceUrl(data);
            }
            else if (inPublisher) {
                media.setPubUrl(data);
            }
            else if (inAuthor) {
                media.setAuthUrl(data);
            }
            else {
                media.setUrl(data);
            }
        }
    }

    public void start_rteinfos(final AttributeList meta) throws SAXException {
        inRTEInfos = true;
    }

    public void end_rteinfos() throws SAXException {
        inRTEInfos = false;
    }

    public void handle_duration(final String data, final AttributeList meta)
            throws SAXException {
        if (inTrkInfos) {
            if (data == null)
                track.setDuration(-1);
            else
                track.setDuration(Integer.valueOf(data));
        }
        else if (inRTEInfos) {
            route.setDuration(Integer.valueOf(data));
        }
    }

    public void start_rtept(final AttributeList meta) throws SAXException {
        inRTEpt = true;
        pointer = new GeoPointer(
                (int) (Double.parseDouble(meta.getValue("lat")) * 1E6),
                (int) (Double.parseDouble(meta.getValue("lon")) * 1E6));
    }

    public void end_rtept() throws SAXException {
        route.addGeoPointer(pointer);
    }

    public void start_source(final AttributeList meta) throws SAXException {
        inSource = true;
    }

    public void end_source() throws SAXException {
        inSource = false;
    }

    public void handle_email(final AttributeList meta) throws SAXException {
        if (inAuthor) {
            gpxa.setEmail(meta.getValue("id") + "@" + meta.getValue("domain"));
        }
    }

    public void handle_bearing(final String data, final AttributeList meta)
            throws SAXException {
        if (inMedia) {
            media.setBearing(Integer.valueOf(data));
        }
    }

    public void handle_radius(final String data, final AttributeList meta)
            throws SAXException {
        if (inMedia) {
            media.setRadius(Integer.valueOf(data));
        }
    }

    public void handle_characteristic(final String data,
            final AttributeList meta) throws SAXException {
        if (inRTEInfos) {
            route.addGpxRteChara(meta.getValue("lang"), data);
        }
    }

    public void handle_maxspeed(final String data, final AttributeList meta)
            throws SAXException {
        if (inRTEInfos) {
            route.setMaxSpeed(Integer.valueOf(data));
        }
    }

    public void start_media(final AttributeList meta) throws SAXException {
        inMedia = true;
        media = new MediaPoint();
    }

    public void end_media() throws SAXException {
        inMedia = false;
        poi.addMedia(media);
    }

    public void start_content(final AttributeList meta) throws SAXException {

    }

    public void handle_content(final String data, final AttributeList meta)
            throws SAXException {
        if (data == null) {
            media.setContent("");
        }
        else {
            media.setContent(data);
        }
    }

    public void end_content() throws SAXException {

    }

    public GPXObject getGPXObject() {
        return gpxa;
    }
}
