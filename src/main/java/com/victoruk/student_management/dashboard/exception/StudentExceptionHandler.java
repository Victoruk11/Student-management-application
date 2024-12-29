package com.victoruk.student_management.dashboard.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException (StudentNotFoundException exc, WebRequest request){

        StudentErrorResponse errorDetails = new StudentErrorResponse();

        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetails.setMessage(exc.getMessage());
        errorDetails.setTimeStamp(System.currentTimeMillis());

        return new  ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StudentErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exc,
                                                                                      WebRequest request) {
        // Check if the exception is due to a unique constraint violation
        String message = exc.getMessage();
        if (message.contains("Duplicate entry")) {
            StudentErrorResponse errorDetails = new StudentErrorResponse();
            errorDetails.setStatus(HttpStatus.CONFLICT.value()); // 409 Conflict
            errorDetails.setMessage("The student with the provided username already exists.");
            errorDetails.setTimeStamp(System.currentTimeMillis());

            return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
        }

        // Handle other integrity violations
        StudentErrorResponse errorDetails = new StudentErrorResponse();
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value()); // 400 Bad Request for other violations
        errorDetails.setMessage("Database integrity violation occurred.");
        errorDetails.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler(StudentConstraintViolationException.class)
//    public ResponseEntity<StudentErrorResponse> handleStudentConstraintViolationException(StudentConstraintViolationException exc,
//                                                                                          WebRequest request) {
//        StudentErrorResponse errorDetails = new StudentErrorResponse();
//        errorDetails.setStatus(HttpStatus.CONFLICT.value());  // Change to 409 Conflict
//        errorDetails.setMessage(exc.getMessage());  // The message can indicate the conflict (e.g., "Student ID already exists")
//        errorDetails.setTimeStamp(System.currentTimeMillis());
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);  // Return 409 Conflict status
//    }


    @ExceptionHandler(StudentServiceUnExpectedError.class)
    public ResponseEntity<StudentErrorResponse> handleStudentServiceUnExpectedError(StudentServiceUnExpectedError exc, WebRequest request) {
        StudentErrorResponse errorDetails = new StudentErrorResponse();
        errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.setMessage(exc.getMessage());
        errorDetails.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler //(Exception.class)
    public final ResponseEntity<StudentErrorResponse> handAllleExceptions(Exception exc, WebRequest request){

        StudentErrorResponse errorDetails =  new StudentErrorResponse();
        errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.setMessage(exc.getMessage());
        errorDetails.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<StudentErrorResponse>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
