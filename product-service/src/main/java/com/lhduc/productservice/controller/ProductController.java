package com.lhduc.productservice.controller;

import com.lhduc.productservice.model.dto.request.ProductCreateRequest;
import com.lhduc.productservice.model.dto.response.ProductDTO;
import com.lhduc.productservice.model.dto.response.SuccessResponse;
import com.lhduc.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.lhduc.productservice.constant.UrlConstant.PRODUCT_ENDPOINT;

@RestController
@RequestMapping(PRODUCT_ENDPOINT)
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    public final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        log.info("Get all products");
        return SuccessResponse.of(products);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductCreateRequest request) {
        productService.createProduct(request);
    }
}
