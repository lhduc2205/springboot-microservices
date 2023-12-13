package com.lhduc.inventoryservice.exception;

public class NotFoundException extends OrderServiceApplicationException {
    public NotFoundException(String message) {
        super(message);
    }
}
