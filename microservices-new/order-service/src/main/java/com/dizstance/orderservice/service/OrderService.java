package com.dizstance.orderservice.service;

import com.dizstance.orderservice.dto.*;
import com.dizstance.orderservice.model.Order;
import com.dizstance.orderservice.model.OrderLine;
import com.dizstance.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public OrderService(OrderRepository orderRepository, WebClient webClient) {
        this.orderRepository = orderRepository;
        this.webClient = webClient;
    }

    public void placeOrder(OrderRequestDTO orderRequestDTO) {
        Order savedOrder = orderMapper(orderRequestDTO);

        List<String> skuCodesList = savedOrder.getOrderLineList()
                .stream()
                .map(OrderLine::getSkuCode)
                .toList();

        // Call inventory service, and place order if product is in stock
        // Necesario hacer la validación porque la lista puede contener 1 solo elemento
        // pero sí alguno de los elementos no tiene stock, toda la orden debería rechazarse
        InventoryResponseDTO[] inventoryResponses = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodesList).build())
                .retrieve()
                .bodyToMono(InventoryResponseDTO[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponseDTO::isInStock);

        if (allProductsInStock) {
            orderRepository.save(savedOrder);
        }else {
            throw new IllegalArgumentException("Product is not in stock, please try again");
        }

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
