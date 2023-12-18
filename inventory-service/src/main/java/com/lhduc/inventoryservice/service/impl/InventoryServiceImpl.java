package com.lhduc.inventoryservice.service.impl;

import com.lhduc.inventoryservice.exception.NotFoundException;
import com.lhduc.inventoryservice.model.dto.request.InventoryCreateRequest;
import com.lhduc.inventoryservice.model.dto.response.InventoryDTO;
import com.lhduc.inventoryservice.model.entity.Inventory;
import com.lhduc.inventoryservice.model.mapper.InventoryMapper;
import com.lhduc.inventoryservice.repository.InventoryRepository;
import com.lhduc.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public List<InventoryDTO> getAllInventory() {
        return InventoryMapper.mapToDTO(inventoryRepository.findAll());
    }

    @Override
    public InventoryDTO getInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inventory not found"));

        return InventoryMapper.mapToDTO(inventory);
    }

    @Override
    public List<InventoryDTO> getInventoriesBySkuCodes(List<String> skuCodes) {
        List<Inventory> inventories = inventoryRepository.findBySkuCodeIn(skuCodes);

        return InventoryMapper.mapToDTO(inventories);
    }

    @Override
    public void createInventory(InventoryCreateRequest request) {

    }
}
