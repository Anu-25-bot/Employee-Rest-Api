package com.example.employee.exception;


public class AlreadyFoundException extends RuntimeException{
    public AlreadyFoundException(String message) {
        super(message); // Passes the message to RuntimeException
    }
}
