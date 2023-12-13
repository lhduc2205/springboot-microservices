package com.lhduc.inventoryservice.repository;

import com.lhduc.inventoryservice.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCode(String skuCode);
}
