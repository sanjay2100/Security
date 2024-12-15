package com.example.security.security.Exceptions.Handlers;

import com.example.security.security.Exceptions.Exceptions.CustomException;
import com.example.security.security.Exceptions.Model.ExceptionModel;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleCustomException {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionModel> throwException(String message,Throwable cause){
        return new ResponseEntity<>(new ExceptionModel(message,cause, HttpStatus.FORBIDDEN),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionModel> throwAuthException(String message,Throwable cause){
        return new ResponseEntity<>(new ExceptionModel(message,cause, HttpStatus.UNAUTHORIZED),HttpStatus.UNAUTHORIZED);
    }
}
