package com.lhduc.orderservice.service.impl;

import com.lhduc.orderservice.common.constant.KafkaConstant;
import com.lhduc.orderservice.common.enums.PaymentStatus;
import com.lhduc.orderservice.exception.InsufficientInventoryException;
import com.lhduc.orderservice.model.dto.request.OrderCreateRequest;
import com.lhduc.orderservice.model.dto.response.OrderLineItemDTO;
import com.lhduc.orderservice.model.dto.response.ProductDTO;
import com.lhduc.orderservice.model.entity.OrderLineItem;
import com.lhduc.orderservice.model.mapper.OrderLineItemMapper;
import com.lhduc.orderservice.service.ProductService;
import com.lhduc.orderservice.exception.NotFoundException;
import com.lhduc.orderservice.model.dto.response.OrderDTO;
import com.lhduc.orderservice.model.entity.Order;
import com.lhduc.orderservice.model.mapper.OrderMapper;
import com.lhduc.orderservice.repository.OrderRepository;
import com.lhduc.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.lhduc.orderservice.common.constant.MessageConstant.ORDER_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ProductService productService;

    @Override
    public List<OrderDTO> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        return OrderMapper.mapToDTO(orders);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(ORDER_NOT_FOUND_MESSAGE));

        return OrderMapper.mapToDTO(order);
    }

    @Transactional
    @Override
    public void createOrder(OrderCreateRequest request) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .paymentStatus(PaymentStatus.UNPAID)
                .build();

        List<OrderLineItem> orderLineItems = new ArrayList<>();

        for (OrderLineItemDTO item : request.getOrderLineItems()) {
            ProductDTO product = productService.getProductByCode(item.getCode());

            if (product.getQuantity() - item.getQuantity() < 0) {
                throw new InsufficientInventoryException(item.getCode(), item.getQuantity(), product.getQuantity());
            }

            OrderLineItem orderLineItem = OrderLineItemMapper.mapToEntity(product);
            orderLineItem.setOrder(order);

            orderLineItems.add(orderLineItem);
        }

        order.setOrderLineItems(orderLineItems);

        orderRepository.save(order);

        kafkaTemplate.send(KafkaConstant.PRODUCT_ORDERED_TOPIC, request.getOrderLineItems());
    }
}
