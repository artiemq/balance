package com.example.exception;

import io.micronaut.http.HttpStatus;

import javax.annotation.Nonnull;

public abstract class ServiceException extends RuntimeException {
    public abstract HttpStatus httpStatus();

    @Nonnull
    public abstract String errorCode();
}
