package com.MrS.possible.domain;

public class add_playlist {          //add playlist
   private int user_ID;
   private String title;
   private String artist;
   private String list_name;

    public add_playlist(int user_ID, String title, String artist,String list_name) {
        this.user_ID = user_ID;
        this.title = title;
        this.artist = artist;
        this.list_name=list_name;
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

    public String getList_name() {
        return list_name;
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

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    @Override
    public String toString() {
        return "add_playlist{" +
                "user_ID=" + user_ID +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", list_name='" + list_name + '\'' +
                '}';
    }
}
