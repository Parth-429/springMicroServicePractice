package com.example.inventoryService.inventoryService.mapper;

import com.example.inventoryService.inventoryService.dto.requestDto.InventoryUpdateRequest;
import com.example.inventoryService.inventoryService.dto.responseDto.InventoryResponse;
import com.example.inventoryService.inventoryService.model.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public Inventory mapToInventoryFromInventoryRequest(InventoryUpdateRequest inventoryUpdateRequest){
        System.out.println(inventoryUpdateRequest);
        return Inventory.builder()
                        .skuCode(inventoryUpdateRequest.getSkuCode())
                        .quantity(inventoryUpdateRequest.getQuantity())
                        .build();
    }

    public InventoryResponse mapToInventoryResponceFromInventory(Inventory inventory){
        System.out.println(inventory);
        return InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .quantity(inventory.getQuantity())
                                .isInStock(inventory.getQuantity()>0)
                                .build();
    }
}
