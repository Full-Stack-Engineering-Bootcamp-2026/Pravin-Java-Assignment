
package com.cdac.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.ApiResponse;
import com.cdac.dto.AuthResponse;
import com.cdac.dto.ChangePasswordDTo;

import com.cdac.dto.LoginInDto;
import com.cdac.dto.SignupDto;

import com.cdac.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> createPassword(@RequestBody @Valid SignupDto dto) {
        System.out.println(dto.toString());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<String>(true, null, userService.saveUser(dto)));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<AuthResponse>> loginUser(@RequestBody @Valid LoginInDto dto) {

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<AuthResponse>(true, null, userService.signinUser(dto)));
    }

}