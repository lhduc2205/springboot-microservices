package com.lhduc.orderservice.service;

import com.lhduc.orderservice.model.dto.response.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO getProductByCode(String code);
    List<ProductDTO> getProductsByCodes(List<String> codes);
}
