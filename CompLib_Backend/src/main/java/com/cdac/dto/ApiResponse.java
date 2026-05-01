package com.cdac.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

	private String message;
	private String timestamp;
	private Boolean success;
	private T data;

	public ApiResponse(Boolean success, String message, T data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.success = success;
		this.message = message;
		this.data = data;
		this.timestamp = LocalDateTime.now().format(formatter);
	}	

	public ApiResponse(Boolean success, String message) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.success = success;
		this.message = message;

		this.timestamp = LocalDateTime.now().format(formatter);
	}

}
