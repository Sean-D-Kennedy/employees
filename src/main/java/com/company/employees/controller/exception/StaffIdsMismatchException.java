package com.company.employees.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class StaffIdsMismatchException extends RuntimeException{
    public StaffIdsMismatchException(String message){
        super(message);
    }
}