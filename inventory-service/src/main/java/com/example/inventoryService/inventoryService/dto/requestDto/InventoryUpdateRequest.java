package com.example.inventoryService.inventoryService.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryUpdateRequest {
    private String skuCode;
    private int quantity;

}

