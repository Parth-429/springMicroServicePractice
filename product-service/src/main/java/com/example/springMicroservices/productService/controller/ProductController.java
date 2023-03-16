package com.example.springMicroservices.productService.controller;

import com.example.springMicroservices.productService.dto.requestDto.ProductRequest;
import com.example.springMicroservices.productService.dto.responseDto.ProductResponse;
import com.example.springMicroservices.productService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
//@RequiredArgsConstructor
public class ProductController {

    private  ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody final ProductRequest productRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productRequest));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @PutMapping("{id}/")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") String id, @RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.updateProducts(id, productRequest));
    }

}
