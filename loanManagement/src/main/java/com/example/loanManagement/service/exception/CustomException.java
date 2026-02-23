package com.example.loanManagement.service.exception;

public class CustomException extends Exception{
    public CustomException(String message){
        super(message);
    }
    public CustomException (Throwable throwable){
        super(throwable);
    }
}
