package com.nva.InventoryService.service.iplm;

import com.nva.InventoryService.dto.InventoryDTOResponse;
import com.nva.InventoryService.repository.InventoryRepository;
import com.nva.InventoryService.service.IInventoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InventoryService implements IInventoryService {

    ModelMapper mapper;
    InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @Override
    public boolean isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

    @Override
    public List<InventoryDTOResponse> isInStockList(List<String> skuCode) {
        List<InventoryDTOResponse> l = inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(inventory ->
                        InventoryDTOResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();

        int i = 1;
        return l;

    }


}
