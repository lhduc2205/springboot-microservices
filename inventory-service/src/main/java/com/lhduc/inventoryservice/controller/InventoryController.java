package com.lhduc.inventoryservice.controller;

import com.lhduc.inventoryservice.model.dto.request.InventoryCreateRequest;
import com.lhduc.inventoryservice.model.dto.response.InventoryDTO;
import com.lhduc.inventoryservice.model.dto.response.SuccessResponse;
import com.lhduc.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.lhduc.inventoryservice.constant.UrlConstant.INVENTORY_ENDPOINT;

@RestController
@RequestMapping(INVENTORY_ENDPOINT)
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse<List<InventoryDTO>> getAllInventory() {
        List<InventoryDTO> inventories = inventoryService.getAllInventory();
        return SuccessResponse.of(inventories);
    }

    @GetMapping("/sku-codes")
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse<List<InventoryDTO>> getInventoriesBySkuCodes(@RequestParam("code") List<String> skuCodes) {
        List<InventoryDTO> inventories = inventoryService.getInventoriesBySkuCodes(skuCodes);
        return SuccessResponse.of(inventories);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInventory(@RequestBody InventoryCreateRequest request) {
        inventoryService.createInventory(request);
    }
}
