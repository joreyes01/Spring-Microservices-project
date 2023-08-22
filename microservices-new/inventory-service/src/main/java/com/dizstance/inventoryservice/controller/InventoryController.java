package com.dizstance.inventoryservice.controller;

import com.dizstance.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    private boolean isInStock (@PathVariable("sku-code") String skuCode) {
        return inventoryService.isInStock(skuCode);
    }

}
