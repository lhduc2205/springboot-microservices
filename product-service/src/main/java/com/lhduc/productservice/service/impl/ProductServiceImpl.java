package com.lhduc.productservice.service.impl;

import com.lhduc.productservice.model.mapper.ProductMapper;
import com.lhduc.productservice.model.dto.request.ProductCreateRequest;
import com.lhduc.productservice.model.dto.response.ProductDTO;
import com.lhduc.productservice.model.entity.Product;
import com.lhduc.productservice.repository.ProductRepository;
import com.lhduc.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return ProductMapper.mapToDTO(products);
    }

    @Override
    public void createProduct(ProductCreateRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }
}
