package com.dizstance.orderservice.dto;

import java.util.List;

public record OrderResponseDTO(
        String orderNumber,
        List<OrderLineResponseDTO> orderLineResponseDTOList

) {
}
