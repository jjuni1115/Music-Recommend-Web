package com.MrS.possible.domain;

public class playlist {
    private int user_ID;
    private String list_name;
    private String release_date;

    public playlist(int user_ID, String list_name, String release_date) {
        this.user_ID = user_ID;
        this.list_name = list_name;
        this.release_date = release_date;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public String getList_name() {
        return list_name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "playlist{" +
                "user_ID=" + user_ID +
                ", list_name='" + list_name + '\'' +
                ", release_date='" + release_date + '\'' +
                '}';
    }
}
