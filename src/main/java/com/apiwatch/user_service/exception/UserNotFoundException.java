package com.apiwatch.user_service.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {

        super(ErrorCode.USER_NOT_FOUND.getMessage());

    }

}
