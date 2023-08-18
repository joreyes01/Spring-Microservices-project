package com.dizstance.orderservice.service;

import com.dizstance.orderservice.dto.OrderLineItemsDTO;
import com.dizstance.orderservice.dto.OrderRequestDTO;
import com.dizstance.orderservice.model.Order;
import com.dizstance.orderservice.model.OrderLineItem;
import com.dizstance.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void placeOrder(OrderRequestDTO orderRequestDTO) {
        Order savedOrder = orderMapper(orderRequestDTO);
        orderRepository.save(savedOrder);
    }

    private Order orderMapper(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemList(
                orderRequestDTO.orderLineItemsDTOList()
                        .stream()
                        .map(this::mapToDto)
                        .toList()
        );
        return order;
    }

    private OrderLineItem mapToDto(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSkuCode(orderLineItemsDTO.skuCode());
        orderLineItem.setPrice(orderLineItemsDTO.price());
        orderLineItem.setQuantity(orderLineItemsDTO.quantity());
        return orderLineItem;
    }

}
