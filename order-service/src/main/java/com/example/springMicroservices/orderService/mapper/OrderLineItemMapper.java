package com.example.springMicroservices.orderService.mapper;
import com.example.springMicroservices.orderService.dto.requestDto.OrderLineItemRequest;
import com.example.springMicroservices.orderService.dto.responceDto.OrderLineItemResponse;
import com.example.springMicroservices.orderService.model.OrderLineItem;
import org.springframework.stereotype.Component;

@Component
public class OrderLineItemMapper {

    public OrderLineItem mapToOrderLineItemFromOrderLineRequest(OrderLineItemRequest orderLineItemRequest){
        return OrderLineItem.builder()
                            .price(orderLineItemRequest.getPrice())
                            .skuCode(orderLineItemRequest.getSkuCode())
                            .quantity(orderLineItemRequest.getQuantity())
                            .build();
    }

    public OrderLineItemResponse mapToOrderLineItemResponseFromOrderLineItem(OrderLineItem orderLineItem){
        return OrderLineItemResponse.builder()
                                    .skuCode(orderLineItem.getSkuCode())
                                    .quantity(orderLineItem.getQuantity())
                                    .build();
    }
}
