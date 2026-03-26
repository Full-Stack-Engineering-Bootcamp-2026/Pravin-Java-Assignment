package com.cdac.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested resource is not found!")
public class ResourceNotFound  extends RuntimeException{
   
	    public ResourceNotFound(String msg) {
	    	
	    	super(msg);
			// TODO Auto-generated constructor stub
		}
}
