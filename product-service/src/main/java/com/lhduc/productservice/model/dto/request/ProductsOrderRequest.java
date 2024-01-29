package com.lhduc.productservice.model.dto.request;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductsOrderRequest {
    private @Valid List<ProductOrderRequest> productOrder;
}
