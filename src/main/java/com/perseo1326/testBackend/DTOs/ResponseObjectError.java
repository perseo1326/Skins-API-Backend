package com.perseo1326.testBackend.DTOs;

public class ResponseObjectError {

    private int statusCode;
    private String statusType;
    private String errorDescription;

    public ResponseObjectError(int statusCode, String statusType, String errorDescription) {
        this.statusCode = statusCode;
        this.statusType = statusType;
        this.errorDescription = errorDescription;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}