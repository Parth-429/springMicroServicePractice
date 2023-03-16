package com.example.springMicroservices.productService.controller;

import com.example.springMicroservices.productService.dto.requestDto.ProductRequest;
import com.example.springMicroservices.productService.dto.responseDto.ProductResponse;
import com.example.springMicroservices.productService.service.ProductService;
import com.example.springMicroservices.productService.utils.productUtils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTestUsingTestControllers {
    @Container
    private static MongoDBContainer mongoDBContainer = new MongoDBContainer();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductService productService;
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
        dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    public void saveProduct() throws Exception {
        ProductRequest productRequest = Utils.getProductRequest();
        ProductResponse expectedResponse = Utils.getProductResponse();
        String productRequestContent = objectMapper.writeValueAsString(productRequest);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                             .request(HttpMethod.POST, "/api/product/")
                                             .contentType(MediaType.APPLICATION_JSON)
                                             .content(productRequestContent))
                                     .andExpect(status().isCreated())
                                     .andReturn();

        String actualProductResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualProductResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(expectedResponse));

    }

    @Test
    public void getProducts() throws Exception{

        List<ProductRequest> productRequests = new ArrayList<>();
        productRequests.add(Utils.getProductRequest());
        productRequests.add(Utils.getProductRequest());
        List<ProductResponse> expectedProductResponses = new ArrayList<>();
        for(ProductRequest productRequest: productRequests){
            expectedProductResponses.add(productService.saveProduct(productRequest));
        }

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                       .request(HttpMethod.GET, "/api/product/")
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk()).andReturn();
        String actualProductResponseList = mvcResult.getResponse().getContentAsString();
        assertThat(actualProductResponseList).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expectedProductResponses));
    }
}
