package com.dizstance.orderservice.dto;

import com.dizstance.orderservice.dto.OrderLineItemsDTO;
import com.dizstance.orderservice.model.OrderLineItem;

import java.util.List;

public record OrderRequestDTO (
        List<OrderLineItemsDTO> orderLineItemsDTOList
) {
}
