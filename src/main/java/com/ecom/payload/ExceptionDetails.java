package com.ecom.payload;

import org.springframework.web.context.request.WebRequest;

import java.util.Date;

public class ExceptionDetails {

    private String message;
    private Date date;
    private String request;

    public ExceptionDetails(String message, String request, Date date) {
        this.message = message;
        this.request = request;
        this.date = date;
    }
}
