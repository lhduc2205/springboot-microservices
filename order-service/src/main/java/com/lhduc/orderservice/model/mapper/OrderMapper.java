package com.lhduc.orderservice.model.mapper;

import com.lhduc.orderservice.model.dto.response.OrderDTO;
import com.lhduc.orderservice.model.entity.Order;

public class OrderMapper {
    private OrderMapper() {

    }

    public static OrderDTO mapToDTO(Order entity) {
        return OrderDTO.builder()
                .id(entity.getId())
                .orderNumber(entity.getOrderNumber())
                .paymentStatus(entity.getPaymentStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .orderLineItems(OrderLineItemMapper.mapToDTO(entity.getOrderLineItems()))
                .build();
    }
}
