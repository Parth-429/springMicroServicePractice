package com.example.springMicroservices.productService.service;

import com.example.springMicroservices.productService.dto.requestDto.ProductRequest;
import com.example.springMicroservices.productService.dto.responseDto.ProductResponse;
import com.example.springMicroservices.productService.mappers.ProductMapper;
import com.example.springMicroservices.productService.model.Product;
import com.example.springMicroservices.productService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public ProductResponse saveProduct(ProductRequest productRequest){
        Product product = productMapper.mapToProductFromProductRequest(productRequest);
        product = productRepository.save(product);
        log.info("Product {} has been saved successfully", product.getId());
        return productMapper.mapToProductResponseFromProduct(product);
    }
    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::mapToProductResponseFromProduct).collect(Collectors.toList());
    }

    public ProductResponse updateProducts(String id, ProductRequest productRequest){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return productMapper.mapToProductResponseFromProduct(productRepository.save(Product.builder()
                                          .id(id)
                                          .name(productRequest.getName())
                                          .price(productRequest.getPrice())
                                          .description(productRequest.getDescription())
                                          .build()));
        }else {
            return null;
        }
    }
}
