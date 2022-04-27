package com.company.common.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends ControllerException {

    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

}
