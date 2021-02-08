package com.MrS.possible.Service;

import javax.inject.Singleton;

@Singleton
public class YoutubeApiConf {
    private String[] API = {"AIzaSyBqqNfLZ8URYN-W1jbReGlsyYMlP8wnTUI", "AIzaSyABoT_EDIh6ydzpPeregb-5jNTe1rjzBTY"};
    private final String MaxCount = "4";
    static public YoutubeApiConf ak;

    private YoutubeApiConf(){}  // Singleton

    static{
         ak = new YoutubeApiConf();
    }

    public static YoutubeApiConf getInstance(){

        return ak;
    }

    public String getAPI(int idx) {
        return API[idx - 1];
    }

    public void setAPI(String[] API) {
        this.API = API;
    }

    public String getMaxCount() {
        return MaxCount;
    }
}
