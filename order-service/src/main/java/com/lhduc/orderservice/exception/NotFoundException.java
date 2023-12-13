package com.lhduc.orderservice.exception;

public class NotFoundException extends OrderServiceApplicationException {
    public NotFoundException(String message) {
        super(message);
    }
}
