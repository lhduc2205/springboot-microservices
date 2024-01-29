package com.lhduc.productservice.service;

import com.lhduc.productservice.model.dto.request.ProductCreateRequest;
import com.lhduc.productservice.model.dto.request.ProductUpdateRequest;
import com.lhduc.productservice.model.dto.response.ProductDTO;
import com.lhduc.productservice.streaming.event.OrderedProductEvent;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductByCode(String code);
    List<ProductDTO> getProductsByCodes(List<String> code);
    void createProduct(ProductCreateRequest request);
    void processOrderedProducts(List<OrderedProductEvent> request);
    void updateProduct(ProductUpdateRequest request);
    void deleteProductByCode(String id);
}
