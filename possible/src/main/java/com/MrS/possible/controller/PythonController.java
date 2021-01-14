package com.MrS.possible.controller;

import com.MrS.possible.Service.MemberService;
import com.MrS.possible.Service.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/py")
public class PythonController {
    @Autowired
    private PythonService pythonService;

    @PostMapping(value="/temp")
    public String python(){
        return "";
    }

}
