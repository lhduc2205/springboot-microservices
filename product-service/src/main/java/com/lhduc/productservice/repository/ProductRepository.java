package com.lhduc.productservice.repository;

import com.lhduc.productservice.model.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
