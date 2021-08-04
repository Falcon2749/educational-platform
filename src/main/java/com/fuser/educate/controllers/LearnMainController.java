package com.fuser.educate.controllers;

import com.fuser.educate.domain.dto.MainUserRequest;
import com.fuser.educate.domain.entity.MainUser;
import com.fuser.educate.repository.MainUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/learn")
@AllArgsConstructor
public class LearnMainController {

    @Autowired
    private final MainUserRepository mainUserRepository;
    private final ModelMapper modelMapper;


    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('cource:read')")
    public String getProfile(){
        return "/learn/profile";
    }

    @GetMapping(value = "/profile.json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MainUserRequest getProfileInfo(Principal principal){

        String username = principal.getName();
        Optional<MainUser> mainUser = mainUserRepository.getMainUserByEmail(username);

        if (mainUser.isEmpty()){
            throw new NoSuchElementException("Користувач з даним email відсутній");
        }

        return modelMapper.map(mainUser.get(), MainUserRequest.class);
    }

}
