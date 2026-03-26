package com.cdac.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
	
		private String message;
		private LocalDateTime   timestamp;
		private boolean success;
		private Object data;
		
		
		public ApiResponse( boolean success, String message , Object data ) {
			// TODO Auto-generated constructor stub
			this.success = success;
			this.message = message;
			this.data = data;
			this.timestamp = LocalDateTime.now();
		}
	
		public ApiResponse( boolean success, String message ) {
			// TODO Auto-generated constructor stub
			this.success = success;
			this.message = message;
		
			this.timestamp = LocalDateTime.now();
		}
	

}
