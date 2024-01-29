package com.lhduc.productservice.exception;

public class ResourceAlreadyExistException extends ProductServiceApplicationException {
    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
