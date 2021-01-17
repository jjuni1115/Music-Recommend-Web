package com.MrS.possible.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.*;

import com.MrS.possible.Service.MemberService;
import com.MrS.possible.Service.PythonService;
import com.MrS.possible.domain.Member;
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

    // Constructor
    public PythonController(final PythonService pythonService){
        this.pythonService = pythonService;
    }

    // Compute Python Script & get recommend Music list & show
    @PostMapping(value="/recommend")
    public String recommend(Member member, HttpSession session){
        Member member_ = new Member(member.getId(), member.getAccount());

        // call recommend class & data analysis + SQL in Python
        pythonService.recommend(member_);
//        session.setAttribute();

        return "/py/recommend";
    }

}
