package com.lhduc.orderservice.exception;

public class OrderServiceApplicationException extends RuntimeException {
    public OrderServiceApplicationException(String message) {
        super(message);
    }
}
