package com.example.universityportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DepartmentMismatchException extends RuntimeException{
    public DepartmentMismatchException(String message) {
        super(message);
    }
}
