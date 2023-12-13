package com.lhduc.inventoryservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.lhduc.inventoryservice.constant.DatabaseConstant.INVENTORY_TABLE_NAME;

@Entity
@Table(name = INVENTORY_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Inventory {
    private static final String SKU_CODE_COLUMN_NAME = "sku_code";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = SKU_CODE_COLUMN_NAME)
    private String skuCode;

    @Column
    private Integer quantity;

    public Inventory(String skuCode, Integer quantity) {
        this.skuCode = skuCode;
        this.quantity = quantity;
    }
}
