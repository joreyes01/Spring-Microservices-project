package com.dizstance.orderservice.controller;

import com.dizstance.orderservice.dto.OrderRequestDTO;
import com.dizstance.orderservice.dto.OrderResponseDTO;
import com.dizstance.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(OrderRequestDTO orderRequestDTO) {
        orderService.placeOrder(orderRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<OrderResponseDTO> listAllOrders () {
        return orderService.getAllOrders();
    }


}
