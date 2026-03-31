package com.swop.port.out;

public interface InventoryService {
    boolean checkStock(String orderId, int quantity);
}
