package com.fuser.educate.controllers;

import com.fuser.educate.domain.dto.MainUserDto;
import com.fuser.educate.domain.entity.MainUser;
import com.fuser.educate.service.MainUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final MainUserService mainUserService;

    @GetMapping("/login")
    public String getLogin(){
        return "/login";
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "/registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@RequestBody MainUserDto mainUserDto){
        System.out.println(mainUserDto);
        mainUserService.register(mainUserDto);
        return "success";
    }

}
