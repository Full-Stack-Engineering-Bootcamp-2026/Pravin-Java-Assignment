package com.cdac.service;

import com.cdac.dto.AuthResponse;
import com.cdac.dto.LoginInDto;
import com.cdac.dto.SignupDto;

import jakarta.validation.Valid;

public interface UserService {

    String saveUser(SignupDto dto);

    AuthResponse signinUser(LoginInDto dto);

}