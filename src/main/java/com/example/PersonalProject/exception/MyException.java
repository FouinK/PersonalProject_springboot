package com.example.PersonalProject.exception;

import org.springframework.stereotype.Component;

@Component
public class MyException extends RuntimeException {

    private String message;

    protected MyException() {
    }

    public MyException(String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
