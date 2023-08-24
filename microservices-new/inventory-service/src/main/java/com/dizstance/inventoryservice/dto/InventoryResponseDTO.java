package com.dizstance.inventoryservice.dto;

public record InventoryResponseDTO(
      String skuCode,
      boolean isInStock
) {
}
