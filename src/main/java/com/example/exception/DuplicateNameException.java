package com.example.exception;

import io.micronaut.http.HttpStatus;

import javax.annotation.Nonnull;

public class DuplicateNameException extends ServiceException {
    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.CONFLICT;
    }

    @Nonnull
    @Override
    public String errorCode() {
        return "name_alredy_taken";
    }
}
