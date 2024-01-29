package com.lhduc.productservice.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.lhduc.productservice.constant.MessageConstant.PRODUCT_CODE_MUST_NOT_BE_BLANK;
import static com.lhduc.productservice.constant.MessageConstant.QUANTITY_MUST_BE_AT_LEAST_1;

@Getter
@Setter
@NoArgsConstructor
public class ProductOrderRequest {

    @NotBlank(message = PRODUCT_CODE_MUST_NOT_BE_BLANK)
    private String code;

    @Min(value = 1, message = QUANTITY_MUST_BE_AT_LEAST_1)
    private Integer quantity;
}
