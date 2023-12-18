package com.lhduc.orderservice.proxy;

import com.lhduc.orderservice.model.dto.response.InventoryDTO;
import com.lhduc.orderservice.model.dto.response.SuccessResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryProxy {
    ResponseEntity<SuccessResponse<List<InventoryDTO>>> getInventoryBySkuCodes(List<String> skuCodes);
}
