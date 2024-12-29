package com.victoruk.student_management.dashboard.exception;

public class StudentErrorResponse {

    private int status;

    private String message;

    private Long TimeStamp;

    public StudentErrorResponse() {

    }

    public StudentErrorResponse(int status, String message, Long timeStamp) {
        this.status = status;
        this.message = message;
        TimeStamp = timeStamp;

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        TimeStamp = timeStamp;
    }
}
