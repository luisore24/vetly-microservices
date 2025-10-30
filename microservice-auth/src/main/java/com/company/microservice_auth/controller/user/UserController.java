package com.company.microservice_auth.controller.user;

import com.company.microservice_auth.common.ApiResponse;
import com.company.microservice_auth.dto.user.UserDTO;
import com.company.microservice_auth.dto.user.UserCreateRequestDTO;
import com.company.microservice_auth.dto.user.UserResponseDTO;
import com.company.microservice_auth.dto.user.UserUpdateRequestDTO;
import com.company.microservice_auth.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> registerUser(@RequestBody UserCreateRequestDTO userCreateRequestDTO){

        ApiResponse<UserResponseDTO> userResponseDTO = userService.register(userCreateRequestDTO);

        return new ResponseEntity<>(userResponseDTO, HttpStatusCode.valueOf(201));

    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(@RequestBody UserUpdateRequestDTO userRequestDTO){

        ApiResponse<UserResponseDTO> userResponseDTO = userService.update(userRequestDTO);

        return new ResponseEntity<>(userResponseDTO, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable("id") Long userId){

        ApiResponse<Void> userDeleted = userService.remove(userId);

        return new ResponseEntity<>(userDeleted, HttpStatusCode.valueOf(200));

    }



}
