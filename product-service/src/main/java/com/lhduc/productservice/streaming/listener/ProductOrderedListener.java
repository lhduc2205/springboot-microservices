package com.lhduc.productservice.streaming.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhduc.productservice.constant.KafkaConstant;
import com.lhduc.productservice.service.ProductService;
import com.lhduc.productservice.streaming.event.OrderedProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductOrderedListener {
    private final ObjectMapper objectMapper;
    private final ProductService productService;

    @Transactional
    @KafkaListener(id = "product-service", topics = KafkaConstant.PRODUCT_ORDERED_TOPIC)
    public void handleProductOrdered(String message) throws JsonProcessingException {
        List<OrderedProductEvent> orderedProducts = objectMapper.readValue(message, new TypeReference<>() {});

        productService.processOrderedProducts(orderedProducts);
    }
}
