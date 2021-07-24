package com.fuser.educate.controllers;

import com.fuser.educate.domain.dto.MainUserDto;
import com.fuser.educate.domain.entity.MainUser;
import com.fuser.educate.exceptions.UserAlreadyExistsException;
import com.fuser.educate.service.MainUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final MainUserService mainUserService;

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "/registration";
    }

    @PostMapping("/registration")
    @ResponseBody
    public String postRegistration(@RequestBody MainUserDto mainUserDto){
        try {
            mainUserService.register(mainUserDto);
            return "Успішно зареєстровано";
        } catch (UserAlreadyExistsException e){
            return e.getMessage();
        }

    }

}
