package com.dipanshu.user_service.Controller;

import com.dipanshu.user_service.service.impl.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserve(@RequestParam Long productId,
                                          @RequestParam int quantity) {

        inventoryService.reserveStock(productId, quantity);
        return ResponseEntity.ok("Stock reserved");
    }

    @PostMapping("/release")
    public ResponseEntity<String> release(@RequestParam Long productId,
                                          @RequestParam int quantity) {

        inventoryService.releaseStock(productId, quantity);
        return ResponseEntity.ok("Stock released");
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestParam Long productId,
                                          @RequestParam int quantity) {

        inventoryService.confirmStock(productId, quantity);
        return ResponseEntity.ok("Stock confirmed");
    }
}