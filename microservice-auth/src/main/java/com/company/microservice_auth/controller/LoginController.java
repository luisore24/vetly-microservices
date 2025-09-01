package com.company.microservice_auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth/login")
public class LoginController {


    @GetMapping()
    public String login(){
        return "Get Login";
    }

    @PostMapping()
    public String loginPost(){
        return "Login Successfull";
    }


}
