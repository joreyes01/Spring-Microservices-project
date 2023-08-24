package com.dizstance.inventoryservice.controller;

import com.dizstance.inventoryservice.dto.InventoryResponseDTO;
import com.dizstance.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    private List<InventoryResponseDTO> isInStock (@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }

}
