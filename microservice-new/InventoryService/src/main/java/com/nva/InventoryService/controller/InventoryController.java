package com.nva.InventoryService.controller;

import com.nva.InventoryService.dto.InventoryDTOResponse;
import com.nva.InventoryService.service.IInventoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InventoryController {

    IInventoryService inventoryService;
//    @GetMapping("/{sku-code}")
//    @ResponseStatus(HttpStatus.OK)
//    public boolean isInStock(@PathVariable("sku-code") String skuCode) {
//        return inventoryService.isInStock(skuCode);
//    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDTOResponse> isInStockList(@RequestParam List<String> skuCode) {
        return inventoryService.isInStockList(skuCode);
    }
}
