package com.example.security.security.Exceptions.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionModel {
    private String message;
    private Throwable cause;
    private HttpStatus status;
}
