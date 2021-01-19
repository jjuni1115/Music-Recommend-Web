package com.MrS.possible.controller;

import com.MrS.possible.Service.MusicService;
import com.MrS.possible.domain.Member;
import com.MrS.possible.domain.result;
import com.google.api.client.json.Json;
import org.json.JSONArray;
import org.json.JSONObject;
import com.MrS.possible.domain.Music;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @PostMapping(value = "/list")
    public List<result> search(String keyword){
        System.out.println(keyword);
        List<result> musicList = new ArrayList<>();
        musicList=musicService.search(keyword);
        System.out.println(musicList);
        return musicList;
    }

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
            for (int i = 0; i < 90; i++) {
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
}
