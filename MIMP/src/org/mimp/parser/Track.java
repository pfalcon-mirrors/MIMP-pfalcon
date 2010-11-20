package org.mimp.parser;

import java.io.Serializable;

/**
 * 
 * @author hellhand
 */
public class Track implements Serializable {

    private static final long serialVersionUID = 510045384921963813L;
    private int trailID = 0;
    private String name = "";
    private String owner = "";
    private int rating = 0;
    private int lon = 0;
    private int lat = 0;
    private double lenght = 0;
    private int distance = 0;
    private int uphill = 0;
    private int downhill = 0;
    private String title = "";
    private int time = 0;
    private int activity = 0;
    private int difficulty = 0;
    private String creationTime = "";

    /**
     * @return the trailID
     */
    public int getTrailID() {
        return trailID;
    }

    /**
     * @param trailID
     *            the trailID to set
     */
    public void setTrailID(int trailID) {
        this.trailID = trailID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner
     *            the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating
     *            the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return the lon
     */
    public int getLon() {
        return lon;
    }

    /**
     * @param lon
     *            the lon to set
     */
    public void setLon(int lon) {
        this.lon = lon;
    }

    /**
     * @return the lat
     */
    public int getLat() {
        return lat;
    }

    /**
     * @param lat
     *            the lat to set
     */
    public void setLat(int lat) {
        this.lat = lat;
    }

    public double getLenght() {
        return lenght;
    }

    public void setLenght(double lenght) {
        this.lenght = lenght;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @param distance
     *            the distance to set
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * @return the uphill
     */
    public int getUphill() {
        return uphill;
    }

    /**
     * @param uphill
     *            the uphill to set
     */
    public void setUphill(int uphill) {
        this.uphill = uphill;
    }

    /**
     * @return the downhill
     */
    public int getDownhill() {
        return downhill;
    }

    /**
     * @param downhill
     *            the downhill to set
     */
    public void setDownhill(int downhill) {
        this.downhill = downhill;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time
     *            the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * @return the activity
     */
    public int getActivity() {
        return activity;
    }

    /**
     * @param activity
     *            the activity to set
     */
    public void setActivity(int activity) {
        this.activity = activity;
    }

    /**
     * @return the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty
     *            the difficulty to set
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return the creationTime
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime
     *            the creationTime to set
     */
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}