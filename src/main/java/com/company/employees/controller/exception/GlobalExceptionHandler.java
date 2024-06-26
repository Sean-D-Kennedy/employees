package com.company.employees.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<DetailedErrorResponse> handleRegNoAlreadyExists(EmployeeAlreadyExistsException exception,
                                                                          WebRequest webRequest){
        DetailedErrorResponse detailedErrorResponse = new DetailedErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        return new ResponseEntity<>(detailedErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<DetailedErrorResponse> handleRegNoNotFound(EmployeeNotFoundException exception,
                                                                     WebRequest webRequest){
        DetailedErrorResponse detailedErrorResponse = new DetailedErrorResponse(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        return new ResponseEntity<>(detailedErrorResponse, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(CarRegNumbersMismatchException.class)
//    public ResponseEntity<DetailedErrorResponse> handleRegNumbersMismatchOnPut(CarRegNumbersMismatchException exception,
//                                                                               WebRequest webRequest){
//        DetailedErrorResponse detailedErrorResponse = new DetailedErrorResponse(
//                webRequest.getDescription(false),
//                HttpStatus.BAD_REQUEST,
//                exception.getMessage()
//        );
//        return new ResponseEntity<>(detailedErrorResponse, HttpStatus.BAD_REQUEST);
//    }
}
