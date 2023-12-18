package com.lhduc.orderservice.service.impl;

import com.lhduc.orderservice.enums.PaymentStatus;
import com.lhduc.orderservice.exception.NotFoundException;
import com.lhduc.orderservice.model.dto.request.OrderCreateRequest;
import com.lhduc.orderservice.model.dto.response.OrderDTO;
import com.lhduc.orderservice.model.entity.Order;
import com.lhduc.orderservice.model.entity.OrderLineItem;
import com.lhduc.orderservice.model.mapper.OrderLineItemMapper;
import com.lhduc.orderservice.model.mapper.OrderMapper;
import com.lhduc.orderservice.repository.OrderRepository;
import com.lhduc.orderservice.service.InventoryService;
import com.lhduc.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.lhduc.orderservice.constant.MessageConstant.ORDER_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryService inventoryService;

    @Override
    public List<OrderDTO> getAllOrder() {
        return new ArrayList<>();
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND_MESSAGE));

        return OrderMapper.mapToDTO(order);
    }

    @Transactional
    @Override
    public void createOrder(OrderCreateRequest request) {
        inventoryService.checkItemsAvailabilityOrThrow(request.getOrderLineItems());

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .paymentStatus(PaymentStatus.UNPAID)
                .build();

        List<OrderLineItem> orderLineItems = OrderLineItemMapper.mapToEntity(request.getOrderLineItems());
        orderLineItems.forEach(item -> item.setOrder(order));

        order.setOrderLineItems(orderLineItems);

        orderRepository.save(order);
    }
}
