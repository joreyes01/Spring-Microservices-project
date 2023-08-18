package com.dizstance.orderservice.dto;

import java.util.List;

public record OrderRequestDTO (
        List<OrderLineDTO> orderLineItemsDTOList
) {
}
