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
    // DB에서 추천 곡 가져와서 출력할 때 필요한 데이터 필드 정의
    public static class Y_M{
        private Long ID;
        private String Account;
        private String Artist;
        private String title;
        private String VideoID;

        public Y_M(Long ID, String account, String artist, String title, String VideoID) {
            this.ID = ID;
            Account = account;
            Artist = artist;
            this.title = title;
            this.VideoID = VideoID;
        }

        public String getVideoID() {
            return VideoID;
        }

        public void setVideoID(String videoID) {
            VideoID = videoID;
        }

        public Long getID() {
            return ID;
        }

        public void setID(Long ID) {
            this.ID = ID;
        }

        public String getAccount() {
            return Account;
        }

        public void setAccount(String account) {
            Account = account;
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
