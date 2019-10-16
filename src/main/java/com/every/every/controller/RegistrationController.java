package com.every.every.controller;

import com.every.every.dto.RegistrationDTO;
import com.every.every.entity.User;
import com.every.every.service.entityService.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

//    TODO мейлсендер и активационный код с проверкой
    @PostMapping("/{id}")
    public String addUser(@RequestBody RegistrationDTO payload){

        return "";
    }
}
