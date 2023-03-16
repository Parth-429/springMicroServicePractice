package com.example.springMicroservices.productService.mappers;

import com.example.springMicroservices.productService.dto.requestDto.ProductRequest;
import com.example.springMicroservices.productService.dto.responseDto.ProductResponse;
import com.example.springMicroservices.productService.model.Product;
import org.springframework.stereotype.Component;

@Component
public final class ProductMapper {

    public Product mapToProductFromProductRequest(ProductRequest productRequest){
        return Product.builder()
                      .name(productRequest.getName())
                      .description(productRequest.getDescription())
                      .price(productRequest.getPrice())
                      .build();
    }

    public ProductResponse mapToProductResponseFromProduct(Product product){
        return ProductResponse.builder()
                              .name(product.getName())
                              .description(product.getDescription())
                              .price(product.getPrice())
                              .build();
    }
}
