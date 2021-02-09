package com.MrS.possible.domain;

public class load_pl {
    private int id;
    private String list_name;

    public load_pl(int id, String list_name) {
        this.id = id;
        this.list_name = list_name;
    }

    public long getId() {
        return id;
    }

    public String getList_name() {
        return list_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    @Override
    public String toString() {
        return "load_playlist{" +
                "id=" + id +
                ", list_name='" + list_name + '\'' +
                '}';
    }
}
