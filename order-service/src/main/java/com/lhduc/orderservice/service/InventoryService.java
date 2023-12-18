package com.lhduc.orderservice.service;

import com.lhduc.orderservice.model.dto.response.InventoryDTO;
import com.lhduc.orderservice.model.dto.response.OrderLineItemDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getInventoryBySkuCodes(List<String> skuCodes);
    void checkItemsAvailabilityOrThrow(List<OrderLineItemDTO> items);
}
