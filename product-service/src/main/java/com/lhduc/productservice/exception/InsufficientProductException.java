package com.lhduc.productservice.exception;

public class InsufficientProductException extends ProductServiceApplicationException {
    public InsufficientProductException(String code, int requestedQuantity, int availableQuantity) {
        super("Insufficient product for code " + code +
                ": Requested quantity is " + requestedQuantity +
                ", but only " + availableQuantity + " is available.");
    }
}
