package com.example.springMicroservices.orderService.utils;

import com.example.springMicroservices.orderService.dto.requestDto.OrderLineItemRequest;
import com.example.springMicroservices.orderService.dto.requestDto.OrderRequest;
import com.example.springMicroservices.orderService.dto.responceDto.OrderLineItemResponse;
import com.example.springMicroservices.orderService.dto.responceDto.OrderResponse;
import com.example.springMicroservices.orderService.model.Order;
import com.example.springMicroservices.orderService.model.OrderLineItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static Order getOrder(){
        return Order.builder()
                    .id(1L)
                    .orderNo("kajsbkajsxbakj")
                    .orderLineItemList(getOrderItemList())
                    .build();
    }

    public static List<OrderLineItem> getOrderItemList(){
        List<OrderLineItem> orderLineItemList = new ArrayList<>();
        orderLineItemList.add(getOrderLineItem());
        return orderLineItemList;
    }

    public static OrderLineItem getOrderLineItem(){
        return OrderLineItem.builder()
                            .id(1L)
                            .quantity(10)
                            .price(BigDecimal.valueOf(120))
                            .skuCode("12121u")
                            .build();
    }

    public static OrderRequest getOrderRequest(){
        return OrderRequest.builder()
                    .orderLineItemList(getOrderItemRequestList())
                    .build();
    }

    public static List<OrderLineItemRequest> getOrderItemRequestList(){
        List<OrderLineItemRequest> orderLineItemList = new ArrayList<>();
        orderLineItemList.add(getOrderLineItemRequest());
        return orderLineItemList;
    }

    public static OrderLineItemRequest getOrderLineItemRequest(){
        return OrderLineItemRequest.builder()
                            .quantity(10)
                            .price(BigDecimal.valueOf(120))
                            .skuCode("12121u")
                            .build();
    }

    public static OrderResponse getOrderResponce(){
        return OrderResponse.builder()
                           .orderLineItemListResponse(getOrderItemResponceList())
                           .build();
    }

    public static List<OrderLineItemResponse> getOrderItemResponceList(){
        List<OrderLineItemResponse> orderLineItemList = new ArrayList<>();
        orderLineItemList.add(getOrderLineItemResponce());
        return orderLineItemList;
    }

    public static OrderLineItemResponse getOrderLineItemResponce(){
        return OrderLineItemResponse.builder()
                                   .quantity(10)
                                   .skuCode("12121u")
                                   .build();
    }

}
