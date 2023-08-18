package com.dizstance.orderservice.dto;

import java.math.BigDecimal;

public record OrderLineItemsDTO (
        String skuCode,
        BigDecimal price,
        Integer quantity
){
}