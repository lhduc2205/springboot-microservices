package com.lhduc.orderservice.model.dto.request;

import com.lhduc.orderservice.model.dto.response.OrderLineItemDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateRequest {
    @Valid
    private List<OrderLineItemDTO> orderLineItems;
}
