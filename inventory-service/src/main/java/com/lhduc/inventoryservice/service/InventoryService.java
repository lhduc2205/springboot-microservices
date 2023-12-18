package com.lhduc.inventoryservice.service;

import com.lhduc.inventoryservice.model.dto.request.InventoryCreateRequest;
import com.lhduc.inventoryservice.model.dto.response.InventoryDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventory();
    InventoryDTO getInventoryById(Long id);
    List<InventoryDTO> getInventoriesBySkuCodes(List<String> skuCode);
    void createInventory(InventoryCreateRequest request);
}
