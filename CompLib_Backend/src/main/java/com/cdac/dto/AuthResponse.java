package com.cdac.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponse {
  private String token;
  private String email;
  

  public AuthResponse(String token, String email) {
    this.token = token;
    this.email = email;
    
  }
}
