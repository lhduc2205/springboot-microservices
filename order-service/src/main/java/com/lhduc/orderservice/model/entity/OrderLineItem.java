package com.lhduc.orderservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.lhduc.orderservice.common.constant.DatabaseConstant.ORDER_LINE_ITEM_TABLE_NAME;

@Entity
@Table(name = ORDER_LINE_ITEM_TABLE_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItem {
    public static final String PRODUCT_CODE_COLUMN_NAME = "code";
    public static final String ORDER_ID_COLUMN_NAME = "order_id";
    public static final String PRODUCT_ID_COLUMN_NAME = "product_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = PRODUCT_CODE_COLUMN_NAME)
    private String productCode;

    @Column
    private Double price;

    @Column
    private Integer quantity;

    @Column(name = PRODUCT_ID_COLUMN_NAME)
    private String productId;

    @ManyToOne
    @JoinColumn(name = ORDER_ID_COLUMN_NAME)
    private Order order;
}
