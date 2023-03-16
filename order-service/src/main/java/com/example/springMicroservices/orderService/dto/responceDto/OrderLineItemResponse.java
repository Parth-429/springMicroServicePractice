package com.example.springMicroservices.orderService.dto.responceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderLineItemResponse {
    private String skuCode;
    private int quantity;
}
