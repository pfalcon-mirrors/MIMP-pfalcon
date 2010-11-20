package org.mimp.parser;

import org.xml.sax.AttributeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author hellhand
 */
public class TracksHandlerImpl implements TracksHandler {
    private Tracks tracks = new Tracks();
    private Track track;

    public void handle_difficulty(final String data, final AttributeList meta)
            throws SAXException {
        if (data == null)
            track.setDifficulty(0);
        else
            track.setDifficulty(Integer.valueOf(data).intValue());
    }

    public void handle_downHill(final String data, final AttributeList meta)
            throws SAXException {
        track.setDownhill(Integer.valueOf(data));
    }

    public void handle_uphill(final String data, final AttributeList meta)
            throws SAXException {
        if (data == null)
            track.setUphill(0);
        else
            track.setUphill(Integer.valueOf(data));
    }

    public void handle_distance(final String data, final AttributeList meta)
            throws SAXException {
        if (data == null)
            track.setDistance(0);
        else
            track.setDistance(Integer.valueOf(data));
    }

    public void handle_title(final String data, final AttributeList meta)
            throws SAXException {
        track.setTitle(data);
    }

    public void handle_created(final String data, final AttributeList meta)
            throws SAXException {
        if (data == null)
            track.setCreationTime("");
        else
            track.setCreationTime(data);
    }

    public void handle_start(final AttributeList meta) throws SAXException {
        track.setLat((int) (Double.parseDouble(meta.getValue("lat")) * 1E6));
        track.setLon((int) (Double.parseDouble(meta.getValue("long")) * 1E6));
    }

    public void start_trail(final AttributeList meta) throws SAXException {
        track = new Track();
        track.setTrailID(Integer.valueOf(meta.getValue("id")));
    }

    public void end_trail() throws SAXException {
        tracks.addTrack(track);
    }

    public void handle_name(final String data, final AttributeList meta)
            throws SAXException {
        track.setName(data);
    }

    public void handle_owner(final String data, final AttributeList meta)
            throws SAXException {
        track.setOwner(data);
    }

    public void handle_rating(final String data, final AttributeList meta)
            throws SAXException {
        if (data == null)
            track.setRating(0);
        else
            track.setRating(Integer.valueOf(data));
    }

    public void start_trails(final AttributeList meta) throws SAXException {

    }

    public void end_trails() throws SAXException {

    }

    public void handle_activity(final String data, final AttributeList meta)
            throws SAXException {
        if (data == null)
            track.setActivity(0);
        else if (Integer.valueOf(data) > 12)
            track.setActivity(0);
        else
            track.setActivity(Integer.valueOf(data));
    }

    public void handle_tps(final String data, final AttributeList meta)
            throws SAXException {
        if (data == null)
            track.setTime(0);
        else
            track.setTime(Integer.valueOf(data));
    }

    public Tracks getTracks() {
        return tracks;
    }
}
