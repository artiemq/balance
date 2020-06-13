package com.example.exception;

import io.micronaut.http.HttpStatus;

import javax.annotation.Nonnull;

public class NotEnough extends ServiceException {
    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }

    @Nonnull
    @Override
    public String errorCode() {
        return "not_enough";
    }
}
