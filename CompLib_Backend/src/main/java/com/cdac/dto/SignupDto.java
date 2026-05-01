package com.cdac.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter

public class SignupDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;
}