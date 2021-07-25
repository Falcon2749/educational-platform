package com.fuser.educate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/learn")
public class LearnMainController {

    @GetMapping("/profile")
    public String getProfile(Model model){

        return "/learn/profile";
    }

}
