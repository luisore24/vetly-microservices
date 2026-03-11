package com.company.microservice_auth.controller.auth;

import com.company.microservice_auth.dto.auth.LoginRequest;
import com.company.microservice_auth.dto.auth.LoginResponse;
import com.company.microservice_auth.service.auth.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @GetMapping()
    public String login1(){
        return "Get Login";
    }

    @GetMapping("validate")
    public String loginPost(){
        return "Login Successfull";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest){

        LoginResponse response = loginService.loginAuthenticate(loginRequest, httpServletRequest);

        if(!response.getStatusCode().equals("200")){
            return new ResponseEntity<LoginResponse>(response, HttpStatusCode.valueOf(403));
        }

        return new ResponseEntity<LoginResponse>(response, HttpStatusCode.valueOf(200));

    }


}
