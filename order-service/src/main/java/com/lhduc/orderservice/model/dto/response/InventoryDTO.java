package com.lhduc.orderservice.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryDTO {
    private String skuCode;
    private Integer quantity;
    private boolean inStock;
}
