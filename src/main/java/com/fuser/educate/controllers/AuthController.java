package com.fuser.educate.controllers;

import com.fuser.educate.domain.dto.MainUserDto;
import com.fuser.educate.exceptions.UserAlreadyExistsException;
import com.fuser.educate.service.MainUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final MainUserService mainUserService;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }


    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }


    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<?> postRegistration(@RequestBody MainUserDto mainUserDto) {
        try {

            mainUserService.register(mainUserDto);
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/registration").toUriString());
            return ResponseEntity.created(uri).build();

        } catch (UserAlreadyExistsException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

}
