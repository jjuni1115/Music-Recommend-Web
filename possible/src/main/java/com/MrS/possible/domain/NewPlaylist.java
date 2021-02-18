package com.MrS.possible.domain;

public class NewPlaylist {
    Long id;
    String first_name;
    String last_name;
    String list_name;
    boolean is_share;
    String release_date;
    public NewPlaylist(Long id, String first_name, String last_name, String list_name,boolean is_share,String release_date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.list_name = list_name;
        this.is_share=is_share;
        this.release_date=release_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public boolean getIs_share() { return is_share; }

    public void setIs_share(boolean is_share) { this.is_share = is_share; }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
