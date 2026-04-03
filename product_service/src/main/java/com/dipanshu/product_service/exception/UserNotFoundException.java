package com.dipanshu.product_service.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String messge){
        super(messge);
    }
}
