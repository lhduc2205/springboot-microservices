package com.lhduc.orderservice.exception;

public class ServiceUnavailableException extends OrderServiceApplicationException {
    public ServiceUnavailableException(String message) {
        super(message);
    }
}
