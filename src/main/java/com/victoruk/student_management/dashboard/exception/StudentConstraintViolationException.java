package com.victoruk.student_management.dashboard.exception;

public class StudentConstraintViolationException extends RuntimeException {

    public StudentConstraintViolationException(String message) {
        super(message);
    }

    public StudentConstraintViolationException(Throwable cause) {
        super(cause);
    }

    public StudentConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
