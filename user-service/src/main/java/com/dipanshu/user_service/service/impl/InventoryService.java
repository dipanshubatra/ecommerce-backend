package com.dipanshu.user_service.service.impl;



public interface InventoryService {

    public void reserveStock(Long productID,int quantity);
    public void releaseStock(Long productID,int quantity);
    public void confirmStock(Long productID,int quantity);

}
