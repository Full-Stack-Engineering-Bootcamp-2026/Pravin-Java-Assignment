package com.cdac.helloworld;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

	// how to change banner.txt

	@GetMapping("/hello")
	public String sayHello() {

		return "Hello, I am Pravin";

	}

	@GetMapping("/bio")
	public String getBio() {
		return new String("I am from CG.");
	}

	@GetMapping("/status")
	public String getStatus() {
		return "Spring is running";

	}

	@GetMapping("/time")
	public String getTime() {
		String date = LocalDate.now().toString();
		return new String(date);
	}

}
