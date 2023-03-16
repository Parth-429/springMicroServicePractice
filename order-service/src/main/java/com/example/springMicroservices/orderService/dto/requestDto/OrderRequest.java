package com.example.springMicroservices.orderService.dto.requestDto;

import com.example.springMicroservices.orderService.model.OrderLineItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderRequest {
    private List<OrderLineItemRequest> orderLineItemList;

}
