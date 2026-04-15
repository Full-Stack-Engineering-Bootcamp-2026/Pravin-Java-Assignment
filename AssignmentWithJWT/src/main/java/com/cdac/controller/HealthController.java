package com.cdac.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.proc.SecurityContext;

@RestController
public class HealthController {

  @GetMapping("/health")
  public String health() {

    return "Application is running!";  
  }
}
