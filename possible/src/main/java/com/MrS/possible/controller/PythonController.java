package com.MrS.possible.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.*;

import com.MrS.possible.Service.MemberService;
import com.MrS.possible.Service.PythonService;
import com.MrS.possible.domain.Member;
import com.MrS.possible.domain.YoutubeDT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @PostMapping(value="/recommend")
    public String recommend(Member member, YoutubeDT youtubedt, HttpSession session){
        Member member_ = new Member(member.getId(), member.getAccount());
        YoutubeDT youtubedt_ = new YoutubeDT(youtubedt.getVideoID());
        // call recommend class & data analysis + SQL in Python
        System.out.println(youtubedt_.getVideoID());
        pythonService.recommend(member_, youtubedt_);
//        session.setAttribute();

        return "/py/recommend";
    }

}
