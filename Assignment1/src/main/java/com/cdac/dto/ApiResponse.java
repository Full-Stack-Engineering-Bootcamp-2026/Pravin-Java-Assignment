package com.cdac.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ApiResponse {

	private String message;
	private LocalDateTime timestamp;
	private boolean success;
	private Object data;

	public ApiResponse(boolean success, String message, Object data) {

		this.success = success;
		this.message = message;
		this.data = data;
		this.timestamp = LocalDateTime.now();
	}

	public ApiResponse(boolean success, String message) {

		this.success = success;
		this.message = message;

		this.timestamp = LocalDateTime.now();
	}

}
