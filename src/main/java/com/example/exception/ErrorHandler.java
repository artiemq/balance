package com.example.exception;

import com.example.model.ErrorDto;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Singleton
@Produces
public class ErrorHandler implements ExceptionHandler<ServiceException, HttpResponse<ErrorDto>> {

    @Override
    public HttpResponse<ErrorDto> handle(HttpRequest request, ServiceException exception) {
        return HttpResponse.status(exception.httpStatus())
                .body(new ErrorDto(exception.errorCode()));
    }
}
