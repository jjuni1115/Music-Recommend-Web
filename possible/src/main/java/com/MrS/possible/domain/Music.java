package com.MrS.possible.domain;

import java.util.Date;

public class Music {
    private int id;
    private String title;
    private String artist;
    private String genre;
    private String release_time;
    private int adult_authen;

    public Music(int id,String title, String artist, String genre, String release_time, int adult_authen) {
        this.id=id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.release_time = release_time;
        this.adult_authen = adult_authen;
    }

    public int getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getRelease_time() {
        return release_time;
    }

    public int isAdult_authen() {
        return adult_authen;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public void setAdult_authen(int adult_authen) {
        this.adult_authen = adult_authen;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", release_time=" + release_time +
                ", adult_authen=" + adult_authen +
                '}';
    }
}
