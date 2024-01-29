package com.lhduc.productservice.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Exception handler method to handle RuntimeException instances.
     *
     * @param exception The RuntimeException instance to handle.
     * @return A ResponseEntity with an ErrorResponse containing the error message and HTTP status code.
     */
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponse> handleGlobalException(RuntimeException exception) {
//        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setErrorMessage(exception.getMessage());
//
//        log.error(exception.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//    }
}
