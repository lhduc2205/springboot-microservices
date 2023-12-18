package com.lhduc.orderservice.service.impl;

import com.lhduc.orderservice.exception.InsufficientInventoryException;
import com.lhduc.orderservice.exception.NotFoundException;
import com.lhduc.orderservice.exception.OrderLineItemOutOfStockException;
import com.lhduc.orderservice.model.dto.response.InventoryDTO;
import com.lhduc.orderservice.model.dto.response.OrderLineItemDTO;
import com.lhduc.orderservice.proxy.InventoryProxy;
import com.lhduc.orderservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryProxy inventoryProxy;

    @Override
    public List<InventoryDTO> getInventoryBySkuCodes(List<String> skuCodes) {
        final var response = inventoryProxy.getInventoryBySkuCodes(skuCodes);

        List<InventoryDTO> inventories = Objects.requireNonNull(response.getBody()).getData();

        if (inventories.isEmpty() || response.getBody() == null) {
            throw new NotFoundException("There are no item in inventory");
        }

        return response.getBody().getData();
    }

    @Override
    public void checkItemsAvailabilityOrThrow(List<OrderLineItemDTO> items) {
        List<String> skuCodes = items.stream().map(OrderLineItemDTO::getSkuCode).toList();
        List<InventoryDTO> inventories = this.getInventoryBySkuCodes(skuCodes);
        Map<String, Integer> inventoriesMap = this.convertInventoryListToSkuQuantityMap(inventories);

        for (OrderLineItemDTO item : items) {
            Integer itemQuantityInInventory = inventoriesMap.get(item.getSkuCode());
            if (itemQuantityInInventory == null || itemQuantityInInventory == 0) {
                throw new OrderLineItemOutOfStockException(item.getSkuCode() + " out of stock");
            }

            if (itemQuantityInInventory - item.getQuantity() < 0) {
                throw new InsufficientInventoryException(item.getSkuCode(), item.getQuantity(), itemQuantityInInventory);
            }
        }
    }

    private Map<String, Integer> convertInventoryListToSkuQuantityMap(List<InventoryDTO> inventories) {
        Map<String, Integer> inventoriesMap = new HashMap<>();
        inventories.forEach(i -> inventoriesMap.put(i.getSkuCode(), i.getQuantity()));

        return inventoriesMap;
    }
}
