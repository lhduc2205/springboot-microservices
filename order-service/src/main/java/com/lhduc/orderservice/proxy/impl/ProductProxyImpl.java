package com.lhduc.orderservice.proxy.impl;

import com.lhduc.orderservice.exception.ServiceUnavailableException;
import com.lhduc.orderservice.exception.handler.RestTemplateResponseErrorHandler;
import com.lhduc.orderservice.proxy.ProductProxy;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

import static com.lhduc.orderservice.common.constant.MessageConstant.PRODUCT_SERVICE_IS_UNAVAILABLE;

@Component
@RequiredArgsConstructor
public class ProductProxyImpl implements ProductProxy {
    public static final String PRODUCT_SERVICE = "Product Service";

    private final RestTemplate restTemplate;

    @Value("${rest-client.product-service-url}")
    private String productServiceUrl;

    /**
     * Configures the RestTemplate with the base URL and error handler for the product service.
     */
    @PostConstruct
    public void config() {
        DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory(productServiceUrl);
        defaultUriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        restTemplate.setUriTemplateHandler(defaultUriBuilderFactory);
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler(PRODUCT_SERVICE));
    }

    /**
     * Retrieves product information by product code.
     *
     * @param code The product code.
     * @return ResponseEntity containing the product information.
     * @throws ServiceUnavailableException if the product service is unavailable.
     */
    @Override
    public ResponseEntity<?> getProductByCode(String code) {
        HttpHeaders headers = createHttpHeaders();

        HttpEntity<String> request = new HttpEntity<>(code, headers);

        return exchangeWithRetry(
                productServiceUrl + "/codes/" + code,
                HttpMethod.GET,
                request
        );
    }

    /**
     * Retrieves product information for multiple product codes.
     *
     * @param codes List of product codes.
     * @return ResponseEntity containing the product information.
     * @throws ServiceUnavailableException if the product service is unavailable.
     */
    @Override
    public ResponseEntity<?> getProductsByCodes(List<String> codes) {
        HttpHeaders headers = createHttpHeaders();

        HttpEntity<List<String>> request = new HttpEntity<>(codes, headers);

        return exchangeWithRetry(
                productServiceUrl + "/codes",
                HttpMethod.POST,
                request
        );
    }

    /**
     * Creates HttpHeaders with JSON content type.
     *
     * @return HttpHeaders object.
     */
    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     * Performs HTTP exchange with retry mechanism.
     *
     * @param url     The URL for the HTTP request.
     * @param method  The HTTP method (e.g., GET, POST).
     * @param request The HTTP request entity.
     * @return ResponseEntity containing the response.
     * @throws ServiceUnavailableException if the product service is unavailable.
     */
    private ResponseEntity<?> exchangeWithRetry(String url, HttpMethod method, HttpEntity<?> request) {
        try {
            return restTemplate.exchange(
                    url,
                    method,
                    request,
                    new ParameterizedTypeReference<>() {
                    }
            );
        } catch (ResourceAccessException e) {
            throw new ServiceUnavailableException(PRODUCT_SERVICE_IS_UNAVAILABLE);
        }
    }
}
