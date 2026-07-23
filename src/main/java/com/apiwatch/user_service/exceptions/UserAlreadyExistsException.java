package com.apiwatch.user_service.exceptions;

public class UserAlreadyExistsException extends Exception {
   
	
	
	public UserAlreadyExistsException(String message){
		super(message);
	}
}
