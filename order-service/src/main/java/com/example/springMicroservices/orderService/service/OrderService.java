package com.example.springMicroservices.orderService.service;

import brave.Span;
import brave.Tracer;
import com.example.springMicroservices.orderService.dto.requestDto.OrderLineItemRequest;
import com.example.springMicroservices.orderService.dto.requestDto.OrderRequest;
import com.example.springMicroservices.orderService.dto.responceDto.OrderResponse;
import com.example.springMicroservices.orderService.mapper.OrderMapper;
import com.example.springMicroservices.orderService.model.InventoryResponse;
import com.example.springMicroservices.orderService.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WebClient.Builder webClient;
    public OrderResponse placeOrder(OrderRequest orderRequest){
        List<String> skuCode = orderRequest.getOrderLineItemList()
                                           .stream()
                                           .map(OrderLineItemRequest::getSkuCode)
                                           .toList();


        InventoryResponse[] inventoryResponses = webClient.build().get()
                                                              .uri("http://inventory-service/api/inventory",
                                                                      uriBuilder -> uriBuilder
                                                                              .queryParam("skuCode", skuCode).build())
                                                              .retrieve().bodyToMono(InventoryResponse[].class).block();
        System.out.println(inventoryResponses.length);
        if (inventoryResponses.length == 0) {
            throw new IllegalArgumentException("Product out of stock");
        }
        boolean isInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        if (isInStock)
            return orderMapper.mapToOrderResponseFromOrder(orderRepository.save(orderMapper.mapToOrderFromOrderRequest(orderRequest)));
        else
            throw new IllegalArgumentException("Item is out of Stock");
    }
}
