package com.company.microservice_auth.controller.user;

import com.company.microservice_auth.dto.user.UserRequestDTO;
import com.company.microservice_auth.dto.user.UserResponseDTO;
import com.company.microservice_auth.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO){

        UserResponseDTO userResponseDTO = userService.register(userRequestDTO);

        return new ResponseEntity<>(userResponseDTO, HttpStatusCode.valueOf(201));

    }

}
