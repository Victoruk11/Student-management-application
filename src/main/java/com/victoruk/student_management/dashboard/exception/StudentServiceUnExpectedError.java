package com.victoruk.student_management.dashboard.exception;

public class StudentServiceUnExpectedError extends  RuntimeException {

    public StudentServiceUnExpectedError(String message) {
        super(message);
    }

    public StudentServiceUnExpectedError(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentServiceUnExpectedError(Throwable cause) {
        super(cause);
    }
}

