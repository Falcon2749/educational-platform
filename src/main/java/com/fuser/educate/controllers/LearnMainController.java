package com.fuser.educate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/learn")
public class LearnMainController {

    @GetMapping("/profile")
    public String getProfile(){
        return "/learn/profile";
    }

}
