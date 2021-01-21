package com.MrS.possible.domain;

import org.apache.ibatis.type.Alias;

// This Class using for Music Recommend Result Type
@Alias("RecResult")
public class RecResult {
    public int userCount;
    private String Count;
    private String PlaylistID;
    private String Artist;
    private String Title;

    public RecResult(){}

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public RecResult(String count, String playlistID, String artist, String title) {
        this.Count = count;
        this.PlaylistID = playlistID;
        this.Artist = artist;
        this.Title = title;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getPlaylistID() {
        return PlaylistID;
    }

    public void setPlaylistID(String playlistID) {
        PlaylistID = playlistID;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
