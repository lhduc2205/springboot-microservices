package com.lhduc.inventoryservice.exception;

public class OrderServiceApplicationException extends RuntimeException {
    public OrderServiceApplicationException() {
    }

    public OrderServiceApplicationException(String message) {
        super(message);
    }

    public OrderServiceApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderServiceApplicationException(Throwable cause) {
        super(cause);
    }

    public OrderServiceApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
