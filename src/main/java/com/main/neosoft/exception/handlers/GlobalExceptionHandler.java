package com.main.neosoft.exception.handlers;

import com.main.neosoft.exception.ExceptionResponse;
import com.main.neosoft.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponse handleResourceNotFound(ResourceNotFoundException exception, WebRequest request) {
        ExceptionResponse error = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), new Date(), request.getDescription(false));
//        error.setStatusCode(HttpStatus.NOT_FOUND.value());
//        error.setTimestamp(new Date());
//        error.setMessage(exception.getMessage());
//        error.setDescription(request.getDescription(false));
        log.warn("Exception: {}", exception);
        return error;
    }
}
