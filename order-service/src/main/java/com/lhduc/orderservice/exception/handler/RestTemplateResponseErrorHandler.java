package com.lhduc.orderservice.exception.handler;

import com.lhduc.orderservice.exception.ServiceUnavailableException;
import lombok.NoArgsConstructor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@NoArgsConstructor
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    private String serviceName = "The service";

    public RestTemplateResponseErrorHandler(String serviceName) {
        super();
        this.serviceName = serviceName;
    }

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().is4xxClientError() || httpResponse.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().is5xxServerError()) {
            throw new ServiceUnavailableException(serviceName + " is currently unavailable. Please try again later.");
        }
    }
}
