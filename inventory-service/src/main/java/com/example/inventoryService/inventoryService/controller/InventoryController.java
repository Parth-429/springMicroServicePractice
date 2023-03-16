package com.example.inventoryService.inventoryService.controller;

import com.example.inventoryService.inventoryService.dto.requestDto.InventoryUpdateRequest;
import com.example.inventoryService.inventoryService.dto.responseDto.InventoryResponse;
import com.example.inventoryService.inventoryService.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/")
    public ResponseEntity<InventoryResponse> saveInventory(@RequestBody InventoryUpdateRequest inventoryUpdateRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.saveInventory(inventoryUpdateRequest));
    }

    @GetMapping
    public List<InventoryResponse> isInStock(@RequestParam(name = "skuCode") List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
