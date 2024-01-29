package com.lhduc.orderservice.common.constant;

/**
 * The {@code DatabaseConstant} class contains constant values representing
 * various database-related information such as table names and column names.
 */
public class DatabaseConstant {
    private DatabaseConstant() {
    }

    public static final String ORDER_TABLE_NAME = "orders";
    public static final String ORDER_LINE_ITEM_TABLE_NAME = "order_line_items";
    public static final String CREATED_AT_COLUMN_NAME = "created_at";
    public static final String UPDATED_AT_COLUMN_NAME = "updated_at";
}
