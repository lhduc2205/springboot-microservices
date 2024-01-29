package com.lhduc.orderservice.controller;

import com.lhduc.orderservice.model.dto.request.OrderCreateRequest;
import com.lhduc.orderservice.model.dto.response.OrderDTO;
import com.lhduc.orderservice.model.dto.response.SuccessResponse;
import com.lhduc.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.lhduc.orderservice.common.constant.UrlConstant.ORDER_ENDPOINT;

@RestController
@RequestMapping(ORDER_ENDPOINT)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse<List<OrderDTO>> getAllOrder() {
        List<OrderDTO> orders = orderService.getAllOrder();
        return SuccessResponse.of(orders);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse<OrderDTO> getOrderById(@PathVariable("id") Long orderId) {
        OrderDTO order = orderService.getOrderById(orderId);
        return SuccessResponse.of(order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @CircuitBreaker(name = INVENTORY)
//    @Retry(name = INVENTORY)
    public void createOrder(@RequestBody @Valid OrderCreateRequest request) {
        orderService.createOrder(request);
    }
}
