package com.MrS.possible.domain;

import org.apache.ibatis.type.Alias;

// This Class using for Music Recommend Result Type
@Alias("RecResult")
public class RecResult {
    private int MusicID;
    private String Count;
    private String PlaylistID;

    public RecResult(){}

    public RecResult(int musicID, String count, String playlistID) {
        this.MusicID = musicID;
        this.Count = count;
        this.PlaylistID = playlistID;
    }

    public int getMusicID() {
        return MusicID;
    }

    public void setMusicID(int musicID) {
        MusicID = musicID;
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
}
