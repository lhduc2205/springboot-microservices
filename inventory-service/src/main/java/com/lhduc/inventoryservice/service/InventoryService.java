package com.lhduc.inventoryservice.service;

import com.lhduc.inventoryservice.model.dto.request.InventoryCreateRequest;
import com.lhduc.inventoryservice.model.dto.response.InventoryDTO;

public interface InventoryService {
    InventoryDTO getInventoryById(Long id);
    void createInventory(InventoryCreateRequest request);
    boolean checkStockAvailability(String skuCode);
}
