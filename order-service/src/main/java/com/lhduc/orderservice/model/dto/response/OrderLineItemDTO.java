package com.lhduc.orderservice.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItemDTO {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
