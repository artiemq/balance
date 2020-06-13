package com.example.exception;

import io.micronaut.http.HttpStatus;

import javax.annotation.Nonnull;

public class NotFoundException extends ServiceException {
    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }

    @Nonnull
    @Override
    public String errorCode() {
        return "wallet.not_found";
    }
}
