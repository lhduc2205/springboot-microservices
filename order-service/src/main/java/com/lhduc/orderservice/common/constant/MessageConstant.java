package com.lhduc.orderservice.common.constant;

/**
 * The {@code MessageConstant} class contains constant values representing
 * various messages used in the application.
 */
public final class MessageConstant {
    private MessageConstant() {

    }

    public static final String ORDER_NOT_FOUND_MESSAGE = "order not found";
    public static final String PRODUCT_CODE_MUST_NOT_BE_BLANK = "Product code must not be blank";
    public static final String PRODUCT_QUANTITY_MUST_BE_AT_LEAST_1 = "Product quantity must be at least 1";
    public static final String PRODUCT_SERVICE_IS_UNAVAILABLE = "Product Service is currently unavailable. Please try again later.";
}
