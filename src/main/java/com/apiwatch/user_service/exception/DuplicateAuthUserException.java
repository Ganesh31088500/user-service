package com.apiwatch.user_service.exception;

public class DuplicateAuthUserException extends RuntimeException {

    public DuplicateAuthUserException() {

        super(ErrorCode.DUPLICATE_AUTH_USER.getMessage());

    }

}
