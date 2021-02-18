package com.MrS.possible.controller;

import com.MrS.possible.Service.MusicService;
import com.MrS.possible.domain.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        Member member_ = new Member(member.getId(), member.getAccount(), member.getFirst_name(), member.getLast_name());
        System.out.println(member_.getFirst_name());
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
    public List<result> search(search_keyword keyword){     //search music information using artist or title
        System.out.println(keyword.getKeyword());
        List<result> musicList = new ArrayList<>();
        if(keyword.getKeyword().equals("title")) {               //search by title
            musicList=musicService.search(keyword.getName());
        }
        else{                                        //search by artist
            musicList=musicService.search_artist(keyword.getName());
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
    public List<result> load_playlist(search_keyword keyword) {
        load_pl temp = new load_pl(Integer.parseInt(keyword.getKeyword()), keyword.getName());
        List<result> playlist = new ArrayList<>();
        playlist = musicService.load(temp);
        System.out.println(playlist);
        return playlist;
    }

    //create new playlist
    @ResponseBody
    @PostMapping(value = "/new_playlist", produces="text/plain")
    public int create_playlist(NewPlaylist keyword){
        Calendar cal =Calendar.getInstance();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String now_time=df.format(cal.getTime());
        NewPlaylist NP = new NewPlaylist(keyword.getId(), keyword.getFirst_name(), keyword.getLast_name(), keyword.getList_name(),keyword.getIs_share(),now_time);
        playlist pl=new playlist(NP.getId().intValue(),NP.getList_name(),now_time);
        musicService.create_playlist(pl);
        musicService.add_sharelist(NP);

        // 사용자 이름 추가해서 share_list 테이블 insert 하기, is_share 컬럼은 기본값 0으로 설정 / 02.14
        // playlist_ID, user_ID, firstName, lastname,list_name, release_time, is_share 컬럼에 넣기
        return 1;
    }


    //load & parsing music information
    @GetMapping("/parsing")
    public void crawling(){
        Calendar cal =Calendar.getInstance();
        DateFormat df=new SimpleDateFormat("yyyyMMdd");
        Date date=null;
        try {
            date=df.parse("20210201");   //setting current date  20131212
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        while(true) {
            List<Music> musicList = new ArrayList<>();
            String curr=df.format(cal.getTime());
            if(curr.equals("20131213")){    //setting target date  20060922
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

    @PostMapping("/share")
    public void share(Member resource, HttpSession session){
        session.setAttribute("member", resource);
    }

    @ResponseBody
    @PostMapping(value="/load_sharelist")
    public List<String> load_sharelist(String data){
        List<String> sharelist=new ArrayList<String>();
        sharelist=musicService.load_sharelist();
        System.out.println("sharelist");
        System.out.println(sharelist);
        return sharelist;
    }

    @ResponseBody
    @PostMapping(value="/sharelist")
    public List<result> sharelist(String keyword) {
        System.out.println("1");
        List<result> playlist = new ArrayList<>();
        playlist = musicService.sharelist(keyword);
        System.out.println(playlist);
        return playlist;
    }
}


class search_keyword{
    String keyword;
    String name;

    public search_keyword(String keyword, String name) {
        this.keyword = keyword;
        this.name = name;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getName() {
        return name;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setName(String name) {
        this.name = name;
    }
}
