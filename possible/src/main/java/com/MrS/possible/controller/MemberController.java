package com.MrS.possible.controller;

import com.MrS.possible.domain.Member;
import com.MrS.possible.Service.MemberService;
import org.apache.log4j.ConsoleAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    private static Logger logger = LoggerFactory.getLogger(MemberController.class);

    // logon page , login Function
    @PostMapping ("/logon")
    public void detail(HttpSession session, Member resources) {
        System.out.println("resources : " + resources);

        // setting member.account, member.password
        Member member = new Member(resources.getAccount(), resources.getPassword());
        // To /member/logon page with  "member" : data  /  (${member.~~}  in .jsp file)
        session.setAttribute("member", memberService.logon(member));
    }

    // youtube Search result return
    @GetMapping ("/logon")
    public void after_search(HttpSession session, Member resources) {
        System.out.println("resources : " + resources);

        // setting member.account, member.password
        Member member = new Member(resources.getAccount(), resources.getPassword());
        // To /member/logon page with  "member" : data  /  (${member.~~}  in .jsp file)
        session.setAttribute("member", memberService.logon(member));
    }

    // Logout, Session invalidation
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();  // Session.Expire

        return "index";
    }

    // To signup page (method : get), When you click sign up button
    @GetMapping(value="/signup")
    public String signup(){
        return "/member/signup";
    }

    // member's detail information page
    @PostMapping(value="/detail")
    public void detail(Member resources, HttpSession session){
        // resources : id, account data
        Member member = new Member(resources.getId(), resources.getAccount());
        // Service.detail impl & DB access for get user's all data
        member = memberService.detail(member);
        // /member/detail page , "member" : data
        session.setAttribute("member", member);
    }

    // Register service and return to Index(Home) Page
    @PostMapping(value="/register")
    public ModelAndView register(Member resources, BindingResult result, RedirectAttributes flash) {
        Member member = new Member(resources.getAccount(), resources.getPassword(),
                resources.getFirst_name(), resources.getLast_name(), resources.getAge(),
                resources.getClass_(), resources.getMoney(), resources.getSex());

        // Service.register impl, register user data
        memberService.register(member);

        // back to index.jsp(home page)
        ModelAndView mv = new ModelAndView();
        ArrayList<String> name = new ArrayList<>();
        name.add(member.getFirst_name());
        name.add(member.getLast_name() + " Congratulation for your Register!");
        mv.addObject("msg", name);
        mv.setViewName("index");
        return mv;
    }

    // AJAX id redundant check
    // int type return X, String Type or Map<> Type O, parameter name Coincide (AJAX, check_id : account)
    @ResponseBody
    @PostMapping(value="/check_id.do", produces="text/plain")
    public String check_id(Member account, HttpSession session){
        Member member = new Member(account.getAccount());
        // check id call
        member = memberService.check_id(member);

        return member.getAccount();
    }

    @PostMapping(value="/infoChange")
    public void infoChange(Member resource ,HttpSession session){
        Member member = new Member(resource.getId(), resource.getAccount(), resource.getPassword(), resource.getFirst_name()
        , resource.getLast_name(), resource.getAge(), resource.getClass_(), resource.getMoney(), resource.getSex());

        session.setAttribute("member", member);
    }


    //    @GetMapping(value = "/")
//    public ModelAndView home() {
//        ModelAndView mv = new ModelAndView();
//        System.out.println("in Controller");
//        mv.addObject("message", "hello world");
//        mv.setViewName("index");
//        return mv;
//    }
}

