package com.lhduc.productservice.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static com.lhduc.productservice.constant.MessageConstant.PRICE_MUST_BE_AT_LEAST_1;
import static com.lhduc.productservice.constant.MessageConstant.PRODUCT_CODE_MUST_NOT_BE_BLANK;
import static com.lhduc.productservice.constant.MessageConstant.QUANTITY_MUST_BE_AT_LEAST_0;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductUpdateRequest {
    @NotBlank(message = PRODUCT_CODE_MUST_NOT_BE_BLANK)
    private String code;

    private String name;

    private String specification;

    @Min(value = 1, message = PRICE_MUST_BE_AT_LEAST_1)
    private Double price;

    @Min(value = 0, message = QUANTITY_MUST_BE_AT_LEAST_0)
    private Integer quantity;
}
