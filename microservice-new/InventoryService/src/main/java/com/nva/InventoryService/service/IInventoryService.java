package com.nva.InventoryService.service;

import com.nva.InventoryService.dto.InventoryDTOResponse;

import java.util.List;

public interface IInventoryService {
    public boolean isInStock(String skuCode);

    public List<InventoryDTOResponse> isInStockList(List<String> skuCode);
}
