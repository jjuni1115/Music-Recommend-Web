package com.MrS.possible.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.MrS.possible.Service.MemberService;
import com.MrS.possible.Service.PythonService;
import com.MrS.possible.domain.Member;
import com.MrS.possible.domain.RecResult;
import com.MrS.possible.domain.YoutubeDT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/py")
public class PythonController {
    @Autowired
    private final PythonService pythonService;
    private Member member;
    private YoutubeDT youtubedt;

    // Constructor
    public PythonController(final PythonService pythonService){
        this.pythonService = pythonService;
    }

    // Compute Python Script & get recommend Music list & show
    @GetMapping(value="/recommend_list")
    public void yourlist(Member resources, YoutubeDT youtubedt, HttpSession session){
        // Save Parameter ID, Account, VideoID
        Member member = new Member(resources.getId(), resources.getAccount());
        YoutubeDT youtubedt_ = new YoutubeDT(youtubedt.getVideoID());

        List<RecResult>[] Rec_Result;
        // call recommend class -> SQL in musicMapper.xml && data analysis + SQL in Python Script
        Rec_Result = pythonService.recommend(member, youtubedt_);

        System.out.println(Rec_Result[0].get(0).getMusicID());
        System.out.println("User 기반 추천 행 수" + Rec_Result[0].size());
        System.out.println("playlist 기반 추천 행 수" + Rec_Result[1].size());

        session.setAttribute("recommend", Rec_Result);
    }
}
