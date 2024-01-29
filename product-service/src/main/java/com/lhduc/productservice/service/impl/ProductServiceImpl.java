package com.lhduc.productservice.service.impl;

import com.lhduc.productservice.exception.InsufficientProductException;
import com.lhduc.productservice.exception.NotFoundException;
import com.lhduc.productservice.exception.ResourceAlreadyExistException;
import com.lhduc.productservice.model.dto.request.ProductUpdateRequest;
import com.lhduc.productservice.model.mapper.ProductMapper;
import com.lhduc.productservice.model.dto.request.ProductCreateRequest;
import com.lhduc.productservice.model.dto.response.ProductDTO;
import com.lhduc.productservice.model.entity.Product;
import com.lhduc.productservice.repository.ProductRepository;
import com.lhduc.productservice.service.ProductService;
import com.lhduc.productservice.streaming.event.OrderedProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.lhduc.productservice.constant.CacheNames.PRODUCT;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = PRODUCT)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public final KafkaTemplate<String, Object> kafkaTemplate;
    public static final String ALL_PRODUCTS_KEY = "'allProducts'";

    @Override
    @Cacheable(key = ALL_PRODUCTS_KEY)
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        log.info("Get all products");
        return ProductMapper.mapToDTO(products);
    }

    @Override
    @Cacheable(key = "#code")
    public ProductDTO getProductByCode(String code) {
        Product product = findProductByCodeOrElseThrow(code);

        log.info("Get product with code = {}", code);
        return ProductMapper.mapToDTO(product);
    }

    @Override
    @Cacheable(key = "#codes", condition = "#codes.size() > 1")
    public List<ProductDTO> getProductsByCodes(List<String> codes) {
        List<Product> products = new ArrayList<>();

        for (String code : codes) {
            Product product = findProductByCodeOrElseThrow(code);
            products.add(product);
        }


        log.info("Get products with codes = {}", codes);

        return ProductMapper.mapToDTO(products);
    }

    @Override
    @CacheEvict(key = ALL_PRODUCTS_KEY)
    public void createProduct(ProductCreateRequest request) {
        if (productRepository.existsByCode(request.getCode())) {
            throw new ResourceAlreadyExistException("Product with code '" + request.getCode() + "' already exists.");
        }

        Product product = Product.builder()
                .name(request.getName())
                .code(request.getCode())
                .specification(request.getSpecification())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

        productRepository.save(product);
        log.info("Creates product from request: {} successfully", request);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void processOrderedProducts(List<OrderedProductEvent> request) {
        List<Product> updatedProducts = new ArrayList<>();

        for (OrderedProductEvent orderedProduct : request) {
            Product product = productRepository.findByCode(orderedProduct.getCode())
                    .orElseThrow(() -> new NotFoundException("Product not found with id = " + orderedProduct.getCode()));

            updateProductQuantityAfterOrder(product, orderedProduct.getQuantity());

            updatedProducts.add(product);
        }

        productRepository.saveAll(updatedProducts);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = ALL_PRODUCTS_KEY),
            @CacheEvict(key = "#request.code"),
    })
    public void updateProduct(ProductUpdateRequest request) {
        Product product = findProductByCodeOrElseThrow(request.getCode());

        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getCode() != null) {
            product.setCode(request.getCode());
        }
        if (request.getSpecification() != null) {
            product.setSpecification(request.getSpecification());
        }
        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }
        if (request.getQuantity() != null) {
            product.setQuantity(request.getQuantity());
        }

        productRepository.save(product);
        log.info("Updates product from request: {} successfully", request);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = ALL_PRODUCTS_KEY),
            @CacheEvict(key = "#code")
    })
    public void deleteProductByCode(String code) {
        productRepository.deleteByCode(code);
        log.info("Deletes product with code = {}", code);
    }

    private void validateProductQuantity(Product product, int orderedQuantity) {
        if (product.getQuantity() < orderedQuantity) {
            throw new InsufficientProductException(product.getCode(), orderedQuantity, product.getQuantity());
        }
    }

    private Product findProductByCodeOrElseThrow(String code) {
        return productRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Product not found with code = " + code));
    }

    private void updateProductQuantityAfterOrder(Product product, int orderedQuantity) {
        int currentQuantity = product.getQuantity();

        if (currentQuantity - orderedQuantity < 0) {
            return;
        }

        product.setQuantity(currentQuantity - orderedQuantity);
    }
}
