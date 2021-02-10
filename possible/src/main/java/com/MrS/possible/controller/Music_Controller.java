package com.MrS.possible.controller;

import com.MrS.possible.Service.MusicService;
import com.MrS.possible.domain.*;
import com.google.api.client.json.Json;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/Music_info")
public class Music_Controller {
    @Autowired
    private MusicService musicService;

    // Go to playlist & recommend page
    @PostMapping(value="/playlist")
    public void recommend(Member member, HttpSession session){
        Member member_ = new Member(member.getId(), member.getAccount());
        session.setAttribute("member", member_);
    }

    @ResponseBody
    @PostMapping(value="/load_my_playlist")
    public List<String> load_my_playlist(String keyword){
        List<String> playlist=new ArrayList<String>();
        playlist=musicService.load_playlist(keyword);
        System.out.println(playlist);
        return playlist;
    }

    //search music
    @ResponseBody
    @PostMapping(value = "/list")
    public List<result> search(String keyword){     //search music information using artist or title
        System.out.println(keyword);
        String[] array =keyword.split("//");  // //를 기준으로 구분 ex) title//keyword
        List<result> musicList = new ArrayList<>();
        if(array[0].equals("title")) {               //search by title
            musicList=musicService.search(array[1]);
        }
        else{                                        //search by artist
            musicList=musicService.search_artist(array[1]);
        }
        return musicList;
    }

    //insert into playlist
    @ResponseBody
    @PostMapping(value = "/insert_playlist")
    public int insert(String keyword){
        System.out.println(keyword);
        String[] array =keyword.split("//");   // //를 기준으로 구분 ex) id//title//artist
        add_playlist param=new add_playlist(Integer.parseInt(array[0]),array[1],array[2],array[3 ]);
        musicService.insert_playlist(param);
        return 1;
    }

    //load my playlist
    @ResponseBody
    @PostMapping(value = "/load_playlist")
    public List<result> load_playlist(String keyword){
        System.out.println(keyword);
        String[] array=keyword.split("//");
        load_pl temp=new load_pl(Integer.parseInt(array[0]),array[1]);
        List<result> playlist = new ArrayList<>();
        playlist=musicService.load(temp);
        System.out.println(playlist);
        return playlist;
    }

    //create new playlist
    @ResponseBody
    @PostMapping(value = "/new_playlist")
    public int create_playlist(String keyword){
        System.out.println(keyword);
        String[] array=keyword.split("//");
        Calendar cal =Calendar.getInstance();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String now_time=df.format(cal.getTime());
        playlist pl=new playlist(Integer.parseInt(array[0]),array[1],now_time);
        musicService.create_playlist(pl);
        return 1;
    }


    //load & parsing music information
    @GetMapping("/parsing")
    public void crawling(){
        Calendar cal =Calendar.getInstance();
        DateFormat df=new SimpleDateFormat("yyyyMMdd");
        Date date=null;
        try {
            date=df.parse("20131212");   //setting current date  20131212
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        while(true) {
            List<Music> musicList = new ArrayList<>();
            String curr=df.format(cal.getTime());
            if(curr.equals("20060922")){    //setting target date  20060922
                break;
            }
            String url = "https://music.bugs.co.kr/chart/track/day/total?chartdate=" + curr;
            System.out.println(curr);
            Elements tmp;
            Elements title;
            Elements artist;
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tmp = doc.select("table tbody tr");
            title = tmp.select("p.title");
            artist = tmp.select("p.artist");
            String artist_name;
            for (int i = 0; i < 95; i++) {
                int adult;
                if (tmp.get(i).attr("multiartist").equals("Y")) {           //Store only representative artists if there are many artists
                    artist_name = artist.get(i).select("a").first().text();
                } else {
                    artist_name = artist.get(i).text();

                }
                if (title.get(i).attr("adult_yn").equals("Y")) {          //setting adult information
                    adult = 1;
                } else {
                    adult = 0;
                }
                Music music = new Music(Integer.parseInt(tmp.get(i).attr("trackid")), title.get(i).text(), artist_name, null, null, adult);
                musicList.add(music);
            }
            cal.add(Calendar.DATE, -1);
            musicService.insert(musicList);
        }
    }

    @GetMapping("/new_song")
    public void crawling_newsong() {                            //saving new song in database

        List<Music> musicList = new ArrayList<>();
        String url="https://music.bugs.co.kr/newest/track/totalpicked?nation=ALL";
        Elements tmp;
        Elements title;
        Elements artist;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tmp = doc.select("table tbody tr");
        title = tmp.select("p.title");
        artist = tmp.select("p.artist");
        String artist_name;
        for (int i = 0; i < 50; i++) {
            int adult;
            if (tmp.get(i).attr("multiartist").equals("Y")) {           //Store only representative artists if there are many artists
                artist_name = artist.get(i).select("a").first().text();
            } else {
                artist_name = artist.get(i).text();

            }
            if (title.get(i).attr("adult_yn").equals("Y")) {          //setting adult information
                adult = 1;
            } else {
                adult = 0;
            }
            Music music = new Music(Integer.parseInt(tmp.get(i).attr("trackid")), title.get(i).text(), artist_name, null, null, adult);
            musicList.add(music);
        }
        musicService.insert(musicList);

    }
}
