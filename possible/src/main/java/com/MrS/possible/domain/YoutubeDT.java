package com.MrS.possible.domain;

import lombok.Builder;

import java.math.BigInteger;

public class YoutubeDT {
    private String videoID;
    private String Duration;
    private BigInteger viewCount;  // viewCount must be BigInteger : api : .getViewCount()
    private String Artist;
    private String title;
    private String thumbnailPath;

    public YoutubeDT(){};

    @Builder(toBuilder = true)
    public YoutubeDT(String videoID, String duration, BigInteger viewCount, String artist, String title, String thumbnailPath) {
        this.videoID = videoID;
        this.Duration = duration;
        this.viewCount = viewCount;
        this.Artist = artist;
        this.title = title;
        this.thumbnailPath = thumbnailPath;
    }

    public YoutubeDT(String videoID) {
        this.videoID = videoID;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public YoutubeDT(String title, String artist){
        this.title = title;
        this.Artist = artist;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public BigInteger getViewCount() {
        return viewCount;
    }

    public void setViewCount(BigInteger viewCount) {
        this.viewCount = viewCount;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
