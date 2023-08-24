package com.dizstance.inventoryservice.service;

import com.dizstance.inventoryservice.dto.InventoryResponseDTO;
import com.dizstance.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> isInStock (List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(
                inventory -> {
                    InventoryResponseDTO inventoryResponseDTO = new InventoryResponseDTO(
                            inventory.getSkuCode(),
                            inventory.getQuantity() > 0);
                    return inventoryResponseDTO;
                }
        ).toList();
    }
}
