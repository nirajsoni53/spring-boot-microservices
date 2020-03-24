package com.rest.webservices.restwebservices.exception;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
class ResponseExceptionDTO {

    private String message;
    private Date date;
    private String description;

}
