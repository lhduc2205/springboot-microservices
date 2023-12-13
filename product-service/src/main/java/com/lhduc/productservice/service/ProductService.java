package com.lhduc.productservice.service;

import com.lhduc.productservice.model.dto.request.ProductCreateRequest;
import com.lhduc.productservice.model.dto.response.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    void createProduct(ProductCreateRequest request);
}
