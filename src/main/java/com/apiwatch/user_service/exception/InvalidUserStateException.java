package com.apiwatch.user_service.exception;

public class InvalidUserStateException extends RuntimeException {

    public InvalidUserStateException() {

        super(ErrorCode.INVALID_USER_STATE.getMessage());

    }

}