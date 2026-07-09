package com.apiwatch.user_service.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException() {

        super(ErrorCode.DUPLICATE_EMAIL.getMessage());

    }

}