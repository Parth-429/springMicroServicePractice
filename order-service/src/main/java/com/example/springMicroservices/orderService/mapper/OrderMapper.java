package com.example.springMicroservices.orderService.mapper;

import com.example.springMicroservices.orderService.dto.requestDto.OrderRequest;
import com.example.springMicroservices.orderService.dto.responceDto.OrderResponse;
import com.example.springMicroservices.orderService.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderMapper {
    @Autowired
    private OrderLineItemMapper orderLineItemMapper;

   public Order mapToOrderFromOrderRequest(OrderRequest orderRequest){
       return Order.builder()
                   .orderNo(UUID.randomUUID().toString())
                   .orderLineItemList( orderRequest
                           .getOrderLineItemList()
                           .stream().map(orderLineItemMapper::mapToOrderLineItemFromOrderLineRequest)
                           .toList())
                   .build();
   }

   public OrderResponse mapToOrderResponseFromOrder(Order order){
       return OrderResponse.builder()
                           .orderLineItemListResponse(order
                                   .getOrderLineItemList()
                                   .stream()
                                   .map(orderLineItemMapper::mapToOrderLineItemResponseFromOrderLineItem)
                                   .toList())
                           .build();
   }

}
