package com.cdac.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChangePasswordDTo {


    @NotBlank
    private String email;
    @NotBlank
    private String newPassword;
   


}
