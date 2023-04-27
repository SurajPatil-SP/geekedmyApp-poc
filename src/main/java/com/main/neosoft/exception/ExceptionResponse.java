package com.main.neosoft.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {

    private Integer statusCode;
    private Date timestamp;
    private String message;
    private String description;

    public ExceptionResponse (Integer statusCode, String message, Date timestamp) {
        this.statusCode =statusCode;
        this.message = message;
        this.timestamp = timestamp;
        this.description = getDescription();

    }

    public ExceptionResponse(Integer statusCode, String message, Date timestamp, String description) {
        this.statusCode =statusCode;
        this.message = message;
        this.timestamp = timestamp;
        this.description =description;

    }
}
