package com.lhduc.orderservice.exception;

public class InsufficientInventoryException extends OrderServiceApplicationException {
    public InsufficientInventoryException(String productCode, int requestedQuantity, int availableQuantity) {
        super("Insufficient inventory with code = " + productCode +
                ": Requested quantity is " + requestedQuantity +
                ", but only " + availableQuantity + " is available.");
    }
}
