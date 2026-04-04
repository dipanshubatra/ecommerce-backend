package com.dipanshu.product_service.service;



public interface InventoryService {

    void reserveStock(Long productID,int quantity);
    void releaseStock(Long productID,int quantity);
    void confirmStock(Long productID,int quantity);

}
