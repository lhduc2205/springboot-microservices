package com.lhduc.orderservice.model.mapper;

import com.lhduc.orderservice.model.dto.response.OrderLineItemDTO;
import com.lhduc.orderservice.model.dto.response.ProductDTO;
import com.lhduc.orderservice.model.entity.OrderLineItem;

import java.util.List;

public class OrderLineItemMapper {
    private OrderLineItemMapper() {
    }

    public static List<OrderLineItem> mapToEntity(List<OrderLineItemDTO> dtos) {
        return dtos.stream().map(OrderLineItemMapper::mapToEntity).toList();
    }

    public static OrderLineItem mapToEntity(OrderLineItemDTO dto) {
        return OrderLineItem.builder()
                .productCode(dto.getCode())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .productId(dto.getProductId())
                .build();
    }

    public static OrderLineItem mapToEntity(ProductDTO dto) {
        return OrderLineItem.builder()
                .productCode(dto.getCode())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .productId(dto.getId())
                .build();
    }

    public static List<OrderLineItemDTO> mapToDTO(List<OrderLineItem> dtos) {
        return dtos.stream().map(OrderLineItemMapper::mapToDTO).toList();
    }

    public static OrderLineItemDTO mapToDTO(OrderLineItem entity) {
        return OrderLineItemDTO.builder()
                .code(entity.getProductCode())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .productId(entity.getProductId())
                .build();
    }
}
