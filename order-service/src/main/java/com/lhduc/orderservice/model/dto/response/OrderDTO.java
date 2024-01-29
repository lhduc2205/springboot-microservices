package com.lhduc.orderservice.model.dto.response;

import com.lhduc.orderservice.common.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class OrderDTO {
    private Long id;
    private String orderNumber;
    private PaymentStatus paymentStatus;
    private Double totalPrice;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<OrderLineItemDTO> orderLineItems;
}
