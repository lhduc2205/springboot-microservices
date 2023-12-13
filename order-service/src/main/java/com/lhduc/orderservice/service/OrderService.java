package com.lhduc.orderservice.service;

import com.lhduc.orderservice.model.dto.request.OrderCreateRequest;
import com.lhduc.orderservice.model.dto.response.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrder();
    OrderDTO getOrderById(Long orderId);
    void createOrder(OrderCreateRequest request);
}
