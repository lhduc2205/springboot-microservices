package com.lhduc.inventoryservice.model.mapper;

import com.lhduc.inventoryservice.model.dto.response.InventoryDTO;
import com.lhduc.inventoryservice.model.entity.Inventory;

public final class InventoryMapper {
    private InventoryMapper() {}

    public static InventoryDTO mapToDTO(Inventory entity) {
        return InventoryDTO.builder().build();
    }
}
