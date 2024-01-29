package com.lhduc.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhduc.productservice.model.dto.request.ProductCreateRequest;
import com.lhduc.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.lhduc.productservice.constant.UrlConstant.PRODUCT_ENDPOINT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
    @Container
    static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    @DisplayName("Test create product")
    void shouldCreateProduct() throws Exception {
        ProductCreateRequest request = this.getProductCreateRequest();
        String requestString = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post(PRODUCT_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestString))
                .andExpect(status().isCreated());

        Assertions.assertEquals(1, productRepository.findAll().size());
    }

    private ProductCreateRequest getProductCreateRequest() {
        return ProductCreateRequest.builder()
                .name("macbook Pro 16")
                .code("macbookPro16")
                .specification("RAM 16GB, SSD 512GB")
                .price(60_000_000d)
                .quantity(14)
                .build();
    }
}
