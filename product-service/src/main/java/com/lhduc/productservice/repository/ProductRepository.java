package com.lhduc.productservice.repository;

import com.lhduc.productservice.model.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByCode(String code);
    void deleteByCode(String code);
    boolean existsByCode(String code);
}
