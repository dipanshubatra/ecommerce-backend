package com.dipanshu.product_service.service.impl;

import com.dipanshu.product_service.entity.Inventory;
import com.dipanshu.product_service.exception.InsufficientStockException;
import com.dipanshu.product_service.exception.InventoryNotFoundException;
import com.dipanshu.product_service.repository.InventoryRepository;
import com.dipanshu.product_service.service.InventoryService;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void reserveStock(Long productId, int quantity) {

        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                Inventory inventory = inventoryRepository.findById(productId)
                        .orElseThrow(() -> new InventoryNotFoundException("Product not found"));

                if (inventory.getAvailableStock() < quantity) {
                    throw new InsufficientStockException("Insufficient stock");
                }

                inventory.setAvailableStock(
                        inventory.getAvailableStock() - quantity
                );

                inventory.setReservedStock(
                        inventory.getReservedStock() + quantity
                );

                inventoryRepository.save(inventory);

                return;

            } catch (ObjectOptimisticLockingFailureException ex) {
                attempt++;

                if (attempt >= maxRetries) {
                    throw new RuntimeException("Concurrent update issue, try again");
                }
            }
        }
    }

    @Override
    public void releaseStock(Long productId, int quantity) {

        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new InventoryNotFoundException("Product not found"));

        if (inventory.getReservedStock() < quantity) {
            throw new InsufficientStockException("Invalid release request");
        }

        inventory.setReservedStock(
                inventory.getReservedStock() - quantity
        );

        inventory.setAvailableStock(
                inventory.getAvailableStock() + quantity
        );

        inventoryRepository.save(inventory);
    }

    @Override
    public void confirmStock(Long productId, int quantity) {

        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (inventory.getReservedStock() < quantity) {
            throw new RuntimeException("Invalid confirm request");
        }

        inventory.setReservedStock(
                inventory.getReservedStock() - quantity
        );

        // availableStock already reduced during reserve

        inventoryRepository.save(inventory);
    }
}