package com.lhduc.productservice.streaming.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderedProductEvent {
    private String code;
    private Integer quantity;
}
