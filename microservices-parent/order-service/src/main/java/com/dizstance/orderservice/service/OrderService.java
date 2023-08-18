package com.dizstance.orderservice.service;

import com.dizstance.orderservice.dto.OrderLineDTO;
import com.dizstance.orderservice.dto.OrderLineResponseDTO;
import com.dizstance.orderservice.dto.OrderRequestDTO;
import com.dizstance.orderservice.dto.OrderResponseDTO;
import com.dizstance.orderservice.model.Order;
import com.dizstance.orderservice.model.OrderLine;
import com.dizstance.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

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

    public List<OrderResponseDTO> getAllOrders () {
        return orderRepository.findAll()
                .stream()
                .map(orderResponseDTOMapper())
                .toList();
    }



    private Order orderMapper(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineList(
                orderRequestDTO.orderLineItemsDTOList()
                        .stream()
                        .map(this::mapToOrderLine)
                        .toList()
        );
        return order;
    }

    private Function<Order, OrderResponseDTO> orderResponseDTOMapper() {
        return (order ->
                new OrderResponseDTO(
                        order.getOrderNumber(),
                        order.getOrderLineList()
                                .stream()
                                .map(this::mapToOrderLineResponseDto)
                                .toList()
                ));
    }

    private OrderLine mapToOrderLine(OrderLineDTO orderLineItemsDTO) {
        OrderLine orderLine = new OrderLine();
        orderLine.setSkuCode(orderLineItemsDTO.skuCode());
        orderLine.setPrice(orderLineItemsDTO.price());
        orderLine.setQuantity(orderLineItemsDTO.quantity());
        return orderLine;
    }

    private OrderLineResponseDTO mapToOrderLineResponseDto(OrderLine orderLine) {
        return new OrderLineResponseDTO(
                orderLine.getSkuCode(),
                orderLine.getPrice(),
                orderLine.getQuantity()
        );
    }

}
