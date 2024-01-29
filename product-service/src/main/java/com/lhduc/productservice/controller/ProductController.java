package com.lhduc.productservice.controller;

import com.lhduc.productservice.model.dto.request.ProductCreateRequest;
import com.lhduc.productservice.model.dto.request.ProductUpdateRequest;
import com.lhduc.productservice.model.dto.response.ProductDTO;
import com.lhduc.productservice.model.dto.response.SuccessResponse;
import com.lhduc.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ProductController {
    public final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return SuccessResponse.of(products);
    }

    @GetMapping("/codes/{code}")
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse<ProductDTO> getProductByCode(@PathVariable("code") String code) {
        ProductDTO product = productService.getProductByCode(code);
        return SuccessResponse.of(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid ProductCreateRequest request) {
        productService.createProduct(request);
    }

    @PostMapping("/codes")
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse<List<ProductDTO>> getProductsByCodes(@RequestBody List<String> codes) {
        List<ProductDTO> products = productService.getProductsByCodes(codes);
        return SuccessResponse.of(products);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody @Valid ProductUpdateRequest request) {
        productService.updateProduct(request);
    }

    @DeleteMapping("/codes/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable("code") String code) {
        productService.deleteProductByCode(code);
    }
}
