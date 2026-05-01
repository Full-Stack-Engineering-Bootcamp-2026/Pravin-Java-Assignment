package com.cdac.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginInDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}