package com.lhduc.productservice.exception;

public class ProductServiceApplicationException extends RuntimeException {
    public ProductServiceApplicationException() {
    }

    public ProductServiceApplicationException(String message) {
        super(message);
    }

    public ProductServiceApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductServiceApplicationException(Throwable cause) {
        super(cause);
    }

    public ProductServiceApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
