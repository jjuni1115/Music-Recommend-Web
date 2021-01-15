package com.MrS.possible.controller;

import com.MrS.possible.Service.MusicService;
import org.json.JSONArray;
import org.json.JSONObject;
import com.MrS.possible.domain.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Music_info")
public class Music_Controller {
    @Autowired
    private MusicService musicService;
    @GetMapping("/parsing")
    public void crawling() {
        List<Music> musicList = new ArrayList<>();
        String json = null;
        String address="https://www.music-flo.com/api/display/v1/browser/chart/3550/track/list?size=100&timestamp=1610536912068";
                // "https://www.music-flo.com/api/display/v1/browser/chart/1/track/list?size=100&timestamp=1610452879438";
                //"https://www.music-flo.com/api/meta/v1/track/KPOP/new?page=1&size=100&timestamp=1581420059879";
        //https://www.music-flo.com/api/display/v1/information/popup/floating/list?timestamp=1610452336819

        BufferedReader br = null;
        URL url = null;
        HttpURLConnection conn = null;
        String protocol="GET";
        try{
            url=new URL(address);
            conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod(protocol);
            br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            json=br.readLine();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jObject=new JSONObject(json);
        JSONObject header= jObject.getJSONObject("data");
        JSONArray jArray=header.getJSONArray("trackList");


        for (int i=0;i<jArray.length();i++){
            JSONObject obj = jArray.getJSONObject(i);
            JSONObject artistList=obj.getJSONObject("representationArtist");
            JSONObject album=obj.getJSONObject("album");
            String artist=artistList.getString("name");
            String releasedate=album.getString("releaseYmd");
            int agencyid=obj.getInt("agencyId");
            String genre=Integer.toString(agencyid);
            SimpleDateFormat before=new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat after =new SimpleDateFormat("yyyy-MM-dd");
            Date temp=null;
            try {
                if(releasedate.length()<8){
                    releasedate=releasedate+"01";
                }
                temp=before.parse(releasedate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String transdate= after.format(temp);
            String name=obj.getString("name");
            String adult=obj.getString("adultAuthYn");
            int adultAuthYn=0;
            if(adult=="Y"){
                adultAuthYn=1;
            }
            int id=obj.getInt("id");
            Music music=new Music(id,name,artist,"Ballad",transdate,adultAuthYn);
            musicList.add(music);
        }
        System.out.println(musicList.getClass());
        musicService.insert(musicList);
    }
}
