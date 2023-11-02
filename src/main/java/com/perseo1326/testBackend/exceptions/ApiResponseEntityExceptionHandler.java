package com.perseo1326.testBackend.exceptions;

import com.perseo1326.testBackend.DTOs.ResponseObjectError;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotValidDataException.class})
    protected ResponseEntity<ResponseObjectError> handleNotValidData(RuntimeException exception, WebRequest webRequest) {
        return getResponseError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    private ResponseEntity<ResponseObjectError> getResponseError(HttpStatus httpStatus, String message) {

        ResponseEntity.BodyBuilder status = ResponseEntity.status(httpStatus);
        status.contentType(MediaType.APPLICATION_JSON);
        return status
                .body(new ResponseObjectError(
                        httpStatus.value(),
                        (httpStatus.value() + "-" + httpStatus.getReasonPhrase()),
                        message));
    }
}
