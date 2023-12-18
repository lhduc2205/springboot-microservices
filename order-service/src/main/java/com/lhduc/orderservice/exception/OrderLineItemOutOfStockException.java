package com.lhduc.orderservice.exception;

public class OrderLineItemOutOfStockException extends OrderServiceApplicationException {
    public OrderLineItemOutOfStockException(String message) {
        super(message);
    }
}
