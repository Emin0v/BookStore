package com.company.common.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ControllerException {

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
