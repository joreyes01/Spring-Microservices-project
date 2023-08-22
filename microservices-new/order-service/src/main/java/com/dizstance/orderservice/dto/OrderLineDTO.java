package com.dizstance.orderservice.dto;

import java.math.BigDecimal;

public record OrderLineDTO(
        String skuCode,
        BigDecimal price,
        Integer quantity
){
}