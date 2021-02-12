package com.MrS.possible.controller;

import com.MrS.possible.Service.YoutubeService;
import com.MrS.possible.dao.YoutubeDaoImpl;
import com.MrS.possible.domain.Member;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.MrS.possible.dao.YoutubeDao;
import com.MrS.possible.domain.YoutubeDT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Controller
@RequestMapping(value="/ytube")
public class YoutubeController {
    @Autowired
    private YoutubeDao youtubeDao = new YoutubeDaoImpl();
    private final YoutubeService youtubeService;

//    private static final YoutubeService youtubeService_search = null;

    public YoutubeController(final YoutubeService youtubeService){
        this.youtubeService = youtubeService;
    }

    // search Music at Youtube, using Youtube API : U must Issue your API key and insert into YoutubeServiceImpl method
//    @ResponseBody
    @GetMapping(value="/searchDo")
    public void search(YoutubeDT resources, Member resources2, HttpSession session) throws GeneralSecurityException, IOException, GoogleJsonResponseException {

        // Make YoutubeDT field & get (title, artist) to search music
        YoutubeDT youtubedt = new YoutubeDT(resources.getTitle(), resources.getArtist());

        Member member = new Member(resources2.getId(), resources2.getAccount());
        System.out.println(youtubedt.getArtist() + " " + youtubedt.getTitle());

        // If song's VideoId is already stored in DB, select videoId from DB & Do not search method(youtube API)
        String tmp;
        if ((tmp = youtubeDao.checkGetvideoID(youtubedt)) != null){
            youtubedt.setVideoID(tmp);
        }
        else{
            // Using Youtube API
            youtubedt = youtubeService.search(youtubedt); // Service.search impl, return MostView videoId

            // Video ID update to DB music table
            youtubeDao.videoidInsert(youtubedt);
        }

        // YoutubeDT class > Y_M class : set necessary Fields
        YoutubeDT.Y_M yt_music = new YoutubeDT.Y_M(member.getId(), member.getAccount(), youtubedt.getArtist(), youtubedt.getTitle(), youtubedt.getVideoID());

        session.setAttribute("youtubedt", yt_music); // 2021.01.19
    }
}
