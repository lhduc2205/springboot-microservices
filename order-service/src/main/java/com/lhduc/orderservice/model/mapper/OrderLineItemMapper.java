package com.lhduc.orderservice.model.mapper;

import com.lhduc.orderservice.model.dto.response.OrderLineItemDTO;
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
                .skuCode(dto.getSkuCode())
                .quantity(dto.getQuantity())
                .build();
    }

    public static List<OrderLineItemDTO> mapToDTO(List<OrderLineItem> dtos) {
        return dtos.stream().map(OrderLineItemMapper::mapToDTO).toList();
    }

    public static OrderLineItemDTO mapToDTO(OrderLineItem dto) {
        return OrderLineItemDTO.builder()
                .skuCode(dto.getSkuCode())
                .quantity(dto.getQuantity())
                .build();
    }
}
