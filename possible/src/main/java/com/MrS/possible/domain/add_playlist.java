package com.MrS.possible.domain;

public class add_playlist {
   private int user_ID;
   private String title;
   private String artist;

    public add_playlist(int user_ID, String title, String artist) {
        this.user_ID = user_ID;
        this.title = title;
        this.artist = artist;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "add_playlist{" +
                "user_ID=" + user_ID +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
