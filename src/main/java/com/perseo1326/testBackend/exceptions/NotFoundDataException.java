package com.perseo1326.testBackend.exceptions;

public class NotFoundDataException extends RuntimeException {

    public NotFoundDataException(String message) {
        super(message);
    }
}
