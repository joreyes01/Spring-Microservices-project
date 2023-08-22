package com.dizstance.orderservice.dto;

import java.math.BigDecimal;

public record OrderLineResponseDTO(
        String skuCode,
        BigDecimal price,
        Integer quantity
) {
}
