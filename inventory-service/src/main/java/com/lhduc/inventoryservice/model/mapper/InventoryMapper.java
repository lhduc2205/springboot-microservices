package com.lhduc.inventoryservice.model.mapper;

import com.lhduc.inventoryservice.model.dto.response.InventoryDTO;
import com.lhduc.inventoryservice.model.entity.Inventory;

import java.util.List;

public final class InventoryMapper {
    private InventoryMapper() {}

    public static InventoryDTO mapToDTO(Inventory entity) {
        return InventoryDTO.builder()
                .skuCode(entity.getSkuCode())
                .quantity(entity.getQuantity())
                .inStock(entity.getQuantity() > 0)
                .build();
    }

    public static List<InventoryDTO> mapToDTO(List<Inventory> entities) {
        return entities.stream().map(InventoryMapper::mapToDTO).toList();
    }
}
