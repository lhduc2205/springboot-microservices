package com.lhduc.inventoryservice.controller;

import com.lhduc.inventoryservice.model.dto.request.InventoryCreateRequest;
import com.lhduc.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.lhduc.inventoryservice.constant.UrlConstant.INVENTORY_ENDPOINT;

@RestController
@RequestMapping(INVENTORY_ENDPOINT)
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("sku/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkStockAvailability(@PathVariable("skuCode") String skuCode) {
        return inventoryService.checkStockAvailability(skuCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInventory(@RequestBody InventoryCreateRequest request) {
        inventoryService.createInventory(request);
    }
}
