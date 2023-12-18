package com.lhduc.orderservice.proxy.impl;

import com.lhduc.orderservice.model.dto.response.InventoryDTO;
import com.lhduc.orderservice.model.dto.response.SuccessResponse;
import com.lhduc.orderservice.proxy.InventoryProxy;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
public class InventoryProxyImpl extends RestTemplate implements InventoryProxy {
    @Value("${rest-client.inventory-service-url}")
    private String inventoryServiceUrl;

    @PostConstruct
    public void config() {
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory(inventoryServiceUrl);
        defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        this.setUriTemplateHandler(defaultUriBuilderFactory);
    }

    @Override
    public ResponseEntity<SuccessResponse<List<InventoryDTO>>> getInventoryBySkuCodes(List<String> skuCodes) {
        URI inventoryServiceUri = UriComponentsBuilder
                .fromUriString(inventoryServiceUrl + "/sku-codes")
                .queryParam("code", skuCodes)
                .build(false)
                .toUri();

        return this.exchange(
                inventoryServiceUri.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                skuCodes
        );
    }
}
