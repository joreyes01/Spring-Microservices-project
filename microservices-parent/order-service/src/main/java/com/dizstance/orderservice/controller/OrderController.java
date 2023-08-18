package com.dizstance.orderservice.controller;

import com.dizstance.orderservice.dto.OrderRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
    @PostMapping
    public String placeOrder(OrderRequestDTO orderRequestDTO) {
        return "Order Placed Succesfully";
    }
}
