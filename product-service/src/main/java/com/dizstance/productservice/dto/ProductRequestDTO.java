package com.dizstance.productservice.dto;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        String description,
        BigDecimal price
) {
}
