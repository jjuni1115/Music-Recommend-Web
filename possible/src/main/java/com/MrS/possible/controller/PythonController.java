package com.MrS.possible.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.MrS.possible.Service.MemberService;
import com.MrS.possible.Service.PythonService;
import com.MrS.possible.domain.Member;
import com.MrS.possible.domain.RecResult;
import com.MrS.possible.domain.YoutubeDT;
import com.MrS.possible.domain.result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    @ResponseBody
    @GetMapping(value="/recommend_list")
    public List<RecResult>[] yourlist(Member resources, YoutubeDT youtubedt, HttpSession session){
        // Save Parameter ID, Account, VideoID
        Member member = new Member(resources.getId(), resources.getAccount());
        YoutubeDT youtubedt_ = new YoutubeDT(youtubedt.getVideoID());

        List<RecResult>[] Rec_Result;
        // call recommend class -> SQL in musicMapper.xml && data analysis + SQL in Python Script
        Rec_Result = pythonService.recommend(member, youtubedt_);

        System.out.println("User 기반 추천 행 수" + Rec_Result[0].size());
        System.out.println("playlist 기반 추천 행 수" + Rec_Result[1].size());

        Rec_Result[0].get(0).userCount = Rec_Result[0].size();  // User Duplicate Count

        return Rec_Result;
//        session.setAttribute("recommend", Rec_Result);
    }
}

//    ModelAndView mv = new ModelAndView();
//        System.out.println("in Controller");
//        mv.addObject("message", "hello world");
//        mv.setViewName("index");
//        return mv;