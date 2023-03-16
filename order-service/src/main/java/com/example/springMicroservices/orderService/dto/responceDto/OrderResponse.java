package com.example.springMicroservices.orderService.dto.responceDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponse {
    private List<OrderLineItemResponse> orderLineItemListResponse;
}
