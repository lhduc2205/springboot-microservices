package com.lhduc.orderservice.exception.handler;

import com.lhduc.orderservice.exception.NotFoundException;
import com.lhduc.orderservice.model.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class DataRequestExceptionHandler {

    /**
     * Exception handler method to handle NotFoundException instances.
     *
     * @param exception The NotFoundException instance to handle.
     * @return A ResponseEntity with an ErrorResponse containing the error message and HTTP status code.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception) {
        log.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
