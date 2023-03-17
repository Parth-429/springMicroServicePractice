package com.example.inventoryService.inventoryService.service;

import com.example.inventoryService.inventoryService.dto.requestDto.InventoryUpdateRequest;
import com.example.inventoryService.inventoryService.dto.responseDto.InventoryResponse;
import com.example.inventoryService.inventoryService.mapper.InventoryMapper;
import com.example.inventoryService.inventoryService.model.Inventory;
import com.example.inventoryService.inventoryService.repository.InventoryRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepo inventoryRepo;
    private final InventoryMapper inventoryMapper;
    public InventoryResponse saveInventory(InventoryUpdateRequest inventoryUpdateRequest){
            return inventoryMapper
                    .mapToInventoryResponceFromInventory(
                            inventoryRepo.save(
                                    inventoryMapper.mapToInventoryFromInventoryRequest(inventoryUpdateRequest)
                            )
                    );
    }
    @Transactional
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode){
        log.info("wait started");
//        Thread.sleep(10000L);
        log.info("wait ended");
        return inventoryRepo.findBySkuCodeIn(skuCode).stream().map(inventoryMapper::mapToInventoryResponceFromInventory).toList();
    }
}
