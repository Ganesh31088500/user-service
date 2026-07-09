package com.apiwatch.user_service.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorResponse {

    private String errorCode;

    private String message;

    private String path;

    private LocalDateTime timestamp;

	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}

}
