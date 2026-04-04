package com.dipanshu.product_service.controller;

import com.dipanshu.product_service.dto.InventoryRequest;
import com.dipanshu.product_service.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserve(@RequestBody InventoryRequest request) {

        inventoryService.reserveStock(request.getProductId(), request.getQuantity());
        return ResponseEntity.ok("Stock reserved");
    }

    @PostMapping("/release")
    public ResponseEntity<String> release(@RequestBody InventoryRequest request) {

        inventoryService.releaseStock(request.getProductId(), request.getQuantity());
        return ResponseEntity.ok("Stock released");
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestBody InventoryRequest request) {

        inventoryService.confirmStock(request.getProductId(), request.getQuantity());
        return ResponseEntity.ok("Stock confirmed");
    }
}