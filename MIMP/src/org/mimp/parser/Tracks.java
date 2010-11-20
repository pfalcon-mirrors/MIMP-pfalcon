package org.mimp.parser;

import java.io.Serializable;
import java.util.Vector;

/**
 * 
 * @author hellhand
 */
public class Tracks implements Serializable {

    private static final long serialVersionUID = 8690092290873004306L;
    private Vector<Track> tracks = new Vector<Track>();

    public Vector<Track> getTracks() {
        return tracks;
    }

    public Track getTrack(int i) {
        return tracks.get(i);
    }

    public Track getTrackById(int id) {
        for (int i = 0; i < tracks.size(); i++) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> track id : "
                    + tracks.get(i).getTrailID());
            if (tracks.get(i).getTrailID() == id) {
                return tracks.get(i);
            }
        }
        return null;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public int countType(int type) {
        int count = 0;
        for (int i = 0; i < tracks.size(); i++) {
            if (tracks.get(i).getActivity() == type) {
                count++;
            }
        }
        return count;
    }

    public Tracks getTracks(int type) {
        Tracks ret = new Tracks();
        for (int i = 0; i < tracks.size(); i++) {
            if (tracks.get(i).getActivity() == type) {
                ret.addTrack(tracks.get(i));
            }
        }
        return ret;
    }

    public int getSize() {
        return tracks.size();
    }

    public Tracks orderOnDifficultyUp() {
        Vector<Track> vtemp = new Vector<Track>();
        Track ttemp;
        for (int i = 0; i < tracks.size(); i = 0) {
            ttemp = tracks.get(i);
            for (int j = 0; j < tracks.size(); j++) {
                if (tracks.get(j).getDifficulty() >= ttemp.getDifficulty()) {
                    ttemp = tracks.get(j);
                }
            }
            vtemp.add(ttemp);
            tracks.remove(ttemp);
        }
        tracks = vtemp;
        return this;
    }

    public Tracks orderOnDifficultyDown() {
        Vector<Track> vtemp = new Vector<Track>();
        Track ttemp;
        for (int i = 0; i < tracks.size(); i = 0) {
            ttemp = tracks.get(i);
            for (int j = 0; j < tracks.size(); j++) {
                if (tracks.get(j).getDifficulty() <= ttemp.getDifficulty()) {
                    ttemp = tracks.get(j);
                }
            }
            vtemp.add(ttemp);
            tracks.remove(ttemp);
        }
        tracks = vtemp;
        return this;
    }

    public Tracks orderOnTimeUp() {
        Vector<Track> vtemp = new Vector<Track>();
        Track ttemp;
        for (int i = 0; i < tracks.size(); i = 0) {
            ttemp = tracks.get(i);
            for (int j = 0; j < tracks.size(); j++) {
                if (tracks.get(j).getTime() >= ttemp.getTime()) {
                    ttemp = tracks.get(j);
                }
            }
            vtemp.add(ttemp);
            tracks.remove(ttemp);
        }
        tracks = vtemp;
        return this;
    }

    public Tracks orderOnTimeDown() {
        Vector<Track> vtemp = new Vector<Track>();
        Track ttemp;
        for (int i = 0; i < tracks.size(); i = 0) {
            ttemp = tracks.get(i);
            for (int j = 0; j < tracks.size(); j++) {
                if (tracks.get(j).getTime() <= ttemp.getTime()) {
                    ttemp = tracks.get(j);
                }
            }
            vtemp.add(ttemp);
            tracks.remove(ttemp);
        }
        tracks = vtemp;
        return this;
    }

    public Tracks orderOnLenghtUp() {
        Vector<Track> vtemp = new Vector<Track>();
        Track ttemp;
        for (int i = 0; i < tracks.size(); i = 0) {
            ttemp = tracks.get(i);
            for (int j = 0; j < tracks.size(); j++) {
                if (tracks.get(j).getLenght() >= ttemp.getLenght()) {
                    ttemp = tracks.get(j);
                }
            }
            vtemp.add(ttemp);
            tracks.remove(ttemp);
        }
        tracks = vtemp;
        return this;
    }

    public Tracks orderOnLenghtDown() {
        Vector<Track> vtemp = new Vector<Track>();
        Track ttemp;
        for (int i = 0; i < tracks.size(); i = 0) {
            ttemp = tracks.get(i);
            for (int j = 0; j < tracks.size(); j++) {
                if (tracks.get(j).getLenght() <= ttemp.getLenght()) {
                    ttemp = tracks.get(j);
                }
            }
            vtemp.add(ttemp);
            tracks.remove(ttemp);
        }
        tracks = vtemp;
        return this;
    }

    public Tracks orderOnDistanceUp() {
        Vector<Track> vtemp = new Vector<Track>();
        Track ttemp;
        for (int i = 0; i < tracks.size(); i = 0) {
            ttemp = tracks.get(i);
            for (int j = 0; j < tracks.size(); j++) {
                if (tracks.get(j).getDistance() >= ttemp.getDistance()) {
                    ttemp = tracks.get(j);
                }
            }
            vtemp.add(ttemp);
            tracks.remove(ttemp);
        }
        tracks = vtemp;
        return this;
    }

    public Tracks orderOnDistanceDown() {
        Vector<Track> vtemp = new Vector<Track>();
        Track ttemp;
        for (int i = 0; i < tracks.size(); i = 0) {
            ttemp = tracks.get(i);
            for (int j = 0; j < tracks.size(); j++) {
                if (tracks.get(j).getDistance() <= ttemp.getDistance()) {
                    ttemp = tracks.get(j);
                }
            }
            vtemp.add(ttemp);
            tracks.remove(ttemp);
        }
        tracks = vtemp;
        return this;
    }

    public Tracks orderOnStarUp() {
        Vector<Track> vtemp = new Vector<Track>();
        Track ttemp;
        for (int i = 0; i < tracks.size(); i = 0) {
            ttemp = tracks.get(i);
            for (int j = 0; j < tracks.size(); j++) {
                if (tracks.get(j).getRating() >= ttemp.getRating()) {
                    ttemp = tracks.get(j);
                }
            }
            vtemp.add(ttemp);
            tracks.remove(ttemp);
        }
        tracks = vtemp;
        return this;
    }

    public Tracks orderOnStarDown() {
        Vector<Track> vtemp = new Vector<Track>();
        Track ttemp;
        for (int i = 0; i < tracks.size(); i = 0) {
            ttemp = tracks.get(i);
            for (int j = 0; j < tracks.size(); j++) {
                if (tracks.get(j).getRating() <= ttemp.getRating()) {
                    ttemp = tracks.get(j);
                }
            }
            vtemp.add(ttemp);
            tracks.remove(ttemp);
        }
        tracks = vtemp;
        return this;
    }

    public Tracks orderOnGoldUp() {
        return orderOnStarUp();
    }

    public Tracks orderOnGoldDown() {
        return orderOnStarDown();
    }
}
