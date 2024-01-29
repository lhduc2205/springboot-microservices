package com.lhduc.orderservice.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhduc.orderservice.exception.NotFoundException;
import com.lhduc.orderservice.exception.OrderServiceApplicationException;
import com.lhduc.orderservice.model.dto.response.ErrorResponse;
import com.lhduc.orderservice.model.dto.response.ProductDTO;
import com.lhduc.orderservice.model.dto.response.SuccessResponse;
import com.lhduc.orderservice.proxy.ProductProxy;
import com.lhduc.orderservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductProxy productProxy;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public ProductDTO getProductByCode(String code) {
        final var response = productProxy.getProductByCode(code);

        if (!response.getStatusCode().is2xxSuccessful()) {
            handleErrorResponse(response);
        }

        String stringResponse = objectMapper.writeValueAsString(response.getBody());
        final SuccessResponse<ProductDTO> responseBody = objectMapper.readValue(stringResponse, new TypeReference<>(){});

        return responseBody.getData();
    }

    @SneakyThrows
    @Override
    public List<ProductDTO> getProductsByCodes(List<String> codes) {
        final var response = productProxy.getProductsByCodes(codes);

        if (!response.getStatusCode().is2xxSuccessful()) {
            handleErrorResponse(response);
        }

        String stringResponse = objectMapper.writeValueAsString(response.getBody());
        final SuccessResponse<List<ProductDTO>> responseBody = objectMapper.readValue(stringResponse, new TypeReference<>(){});

        List<ProductDTO> products = responseBody.getData();

        if (products.isEmpty()) {
            throw new NotFoundException("There are no product");
        }

        return products;
    }

    @SneakyThrows
    private void handleErrorResponse(ResponseEntity<?> response) {
        String stringResponse = objectMapper.writeValueAsString(response.getBody());
        ErrorResponse errorResponse = objectMapper.readValue(stringResponse, ErrorResponse.class);

        if (response.getStatusCode().is4xxClientError()) {
            throw new NotFoundException(errorResponse.getErrorMessage());
        }

        throw new OrderServiceApplicationException(errorResponse.getErrorMessage());
    }
}
