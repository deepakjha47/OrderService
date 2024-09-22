package com.dpanalytics.OrderService.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{
    private String errorCode;
    private int status;

    public CustomException(String message, String errorCode, int status){
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }
}
