package com.company.common.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends ControllerException {

    public ValidationException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}