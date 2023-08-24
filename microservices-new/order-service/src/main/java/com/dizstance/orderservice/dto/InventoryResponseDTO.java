package com.dizstance.orderservice.dto;

public record InventoryResponseDTO(
      String skuCode,
      boolean isInStock
) {
}
