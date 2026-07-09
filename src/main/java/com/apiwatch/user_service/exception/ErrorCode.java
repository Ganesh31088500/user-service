package com.apiwatch.user_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND("USR_001","User not found"),

    DUPLICATE_EMAIL("USR_002","Email already exists"),

    DUPLICATE_AUTH_USER("USR_003","User profile already exists"),

    INVALID_USER_STATE("USR_004","Invalid user state"),

    VALIDATION_ERROR("USR_005","Validation failed"),

    INTERNAL_SERVER_ERROR("USR_999","Internal server error");

    ErrorCode(String code, String message) {
		this.code=code;
		this.message=message;
	}

	private final String code;

    private final String message;

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}



}