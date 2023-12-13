package com.lhduc.orderservice.controller;

import com.lhduc.orderservice.model.dto.request.OrderCreateRequest;
import com.lhduc.orderservice.model.dto.response.OrderDTO;
import com.lhduc.orderservice.model.dto.response.SuccessResponse;
import com.lhduc.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.lhduc.orderservice.constant.UrlConstant.ORDER_ENDPOINT;

@RestController
@RequestMapping(ORDER_ENDPOINT)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse<OrderDTO> getOrderById(@PathVariable("id") Long orderId) {
        OrderDTO order = orderService.getOrderById(orderId);
        return SuccessResponse.of(order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderCreateRequest request) {
        orderService.createOrder(request);
    }
}
