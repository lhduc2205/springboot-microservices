package com.lhduc.orderservice.enums;

public enum PaymentStatus {
    UNPAID,
    PAID,
    CANCELLED;

    public boolean isEditable() {
        return this == UNPAID;
    }

    public boolean isNotEditable() {
        return this == PAID || this == CANCELLED;
    }
}
