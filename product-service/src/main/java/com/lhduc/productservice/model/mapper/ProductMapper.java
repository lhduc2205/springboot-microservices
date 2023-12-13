package com.lhduc.productservice.model.mapper;

import com.lhduc.productservice.model.dto.response.ProductDTO;
import com.lhduc.productservice.model.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    private ProductMapper() {
    }

    public static List<ProductDTO> mapToDTO(List<Product> products) {
        return products.stream().map(ProductMapper::mapToDTO).toList();
    }

    public static ProductDTO mapToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
