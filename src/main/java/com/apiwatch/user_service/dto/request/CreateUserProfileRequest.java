package com.apiwatch.user_service.dto.request;

import java.util.UUID;

import com.apiwatch.user_service.enums.Role;

public class CreateUserProfileRequest {

    private UUID authUserId;

    private String username;

    private String email;

    private String role;

	public UUID getAuthUserId() {
		return authUserId;
	}

	public void setAuthUserId(UUID authUserId) {
		this.authUserId = authUserId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


    
    
    

}