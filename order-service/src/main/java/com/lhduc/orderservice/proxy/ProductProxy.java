package com.lhduc.orderservice.proxy;

import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * The {@code ProductProxy} interface defines methods for retrieving product information.
 * Implementations of this interface are responsible for interacting with external services
 * or data sources to fetch product data.
 */
public interface ProductProxy {

    /**
     * Retrieves product information based on a single product code.
     *
     * @param code The unique code of the product to retrieve.
     * @return A {@code ResponseEntity<?>} containing the product information.
     *         The actual type of the response entity may vary based on the implementation.
     */
    ResponseEntity<?> getProductByCode(String code);

    /**
     * Retrieves product information for multiple product codes.
     *
     * @param codes A {@code List<String>} containing the codes of the products to retrieve.
     * @return A {@code ResponseEntity<?>} containing the product information.
     *         The actual type of the response entity may vary based on the implementation.
     */
    ResponseEntity<?> getProductsByCodes(List<String> codes);
}
