package com.example.PersonalProject.exception;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.example")
public class ExceptionController extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyException.class);

    @ExceptionHandler(MyException.class)
    public ResponseEntity<?> loginException(MyException e) {
        System.out.println(e.getMessage());
        System.out.println("예외처리");
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

}
