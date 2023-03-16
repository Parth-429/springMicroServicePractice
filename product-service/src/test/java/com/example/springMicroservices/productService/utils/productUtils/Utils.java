package com.example.springMicroservices.productService.utils.productUtils;

import com.example.springMicroservices.productService.dto.requestDto.ProductRequest;
import com.example.springMicroservices.productService.dto.responseDto.ProductResponse;

import java.math.BigDecimal;

public class Utils {
    public static ProductRequest getProductRequest(){
        return ProductRequest.builder()
                             .name("item1")
                             .description("Description for item1")
                             .price(BigDecimal.valueOf(120))
                             .build();
    }

    public static ProductResponse getProductResponse(){
        return ProductResponse.builder()
                      .name("item1")
                      .description("Description for item1")
                      .price(BigDecimal.valueOf(120))
                      .build();
    }

    public static String getProductID(){
        return "lasjnalcajl";
    }
}
