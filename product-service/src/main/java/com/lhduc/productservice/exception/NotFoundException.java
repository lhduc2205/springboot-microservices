package com.lhduc.productservice.exception;

public class NotFoundException extends ProductServiceApplicationException {
    public NotFoundException(String message) {
        super(message);
    }
}
