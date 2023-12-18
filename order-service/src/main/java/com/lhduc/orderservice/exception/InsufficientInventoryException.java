package com.lhduc.orderservice.exception;

public class InsufficientInventoryException extends OrderServiceApplicationException {
    public InsufficientInventoryException(String skuCode, int requestedQuantity, int availableQuantity) {
        super("Insufficient inventory for SKU " + skuCode +
                ": Requested quantity is " + requestedQuantity +
                ", but only " + availableQuantity + " is available.");
    }
}
