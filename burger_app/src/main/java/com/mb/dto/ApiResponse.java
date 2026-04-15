package com.mb.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

	private String message;
	private LocalDateTime timestamp;
	private Boolean success;
	private T data;

	public ApiResponse(Boolean success, String message, T data) {

		this.success = success;
		this.message = message;
		this.data = data;
		this.timestamp = LocalDateTime.now();
	}

	public ApiResponse(Boolean success, String message) {

		this.success = success;
		this.message = message;

		this.timestamp = LocalDateTime.now();
	}

}
