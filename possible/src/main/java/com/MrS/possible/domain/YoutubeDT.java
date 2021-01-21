package com.MrS.possible.domain;

import lombok.Builder;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.type.Alias;

import java.math.BigInteger;

@Alias("YoutubeDT")
public class YoutubeDT {
    private String videoID;
    private String Duration;
    private BigInteger viewCount;  // viewCount must be BigInteger : api : .getViewCount()
    private String Artist;
    private String title;
    private String thumbnailPath;


    public YoutubeDT(){};

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

    public YoutubeDT(String title, String Artist){
        this.title = title;
        this.Artist = Artist;
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

    public void setArtist(String Artist) {
        this.Artist = Artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // DB에서 추천 곡 가져와서 출력할 때 필요한 데이터 필드 정의
    public static class Y_M{
        private Long ID;
        private String Account;
        private String Artista;
        private String title;
        private String VideoID;

        public Y_M(Long ID, String account, String artista, String title, String VideoID) {
            this.ID = ID;
            Account = account;
            Artista = artista;
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
            return Artista;
        }

        public void setArtist(String artista) {
            Artista = artista;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
