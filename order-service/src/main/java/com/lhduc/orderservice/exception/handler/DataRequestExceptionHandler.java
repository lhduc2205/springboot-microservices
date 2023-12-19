package com.lhduc.orderservice.exception.handler;

import com.lhduc.orderservice.exception.InsufficientInventoryException;
import com.lhduc.orderservice.exception.NotFoundException;
import com.lhduc.orderservice.model.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class DataRequestExceptionHandler {

    /**
     * Exception handler method to handle instances of {@link NotFoundException}.
     * Logs the error message and returns a {@link ResponseEntity} with the error details.
     *
     * @param exception The instance of {@link NotFoundException} to handle.
     * @return A {@link ResponseEntity} containing an {@link ErrorResponse} with the error message.
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception) {
        log.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Exception handler method to handle instances of {@link InsufficientInventoryException}.
     * Logs the error message and returns a {@link ResponseEntity} with the error details.
     *
     * @param exception The instance of {@link InsufficientInventoryException} to handle.
     * @return A {@link ResponseEntity} containing an {@link ErrorResponse} with the error message.
     */
    @ExceptionHandler(InsufficientInventoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleInsufficientInventoryException(InsufficientInventoryException exception) {
        log.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
