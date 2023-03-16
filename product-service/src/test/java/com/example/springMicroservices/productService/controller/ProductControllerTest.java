package com.example.springMicroservices.productService.controller;

import com.example.springMicroservices.productService.dto.requestDto.ProductRequest;
import com.example.springMicroservices.productService.dto.responseDto.ProductResponse;
import com.example.springMicroservices.productService.service.ProductService;
import com.example.springMicroservices.productService.utils.productUtils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
@WebMvcTest
class ProductControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ProductService productService;

    @InjectMocks
    ProductController productController;

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
    public void updateProduct() throws Exception {
        ProductRequest productRequest = Utils.getProductRequest();
        String pId = Utils.getProductID();
        String productRequestContent = objectMapper.writeValueAsString(productRequest);
        mockMvc.perform(MockMvcRequestBuilders
                .request(HttpMethod.PUT,"/api/product/"+pId+"/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productRequestContent))
               .andExpect(status().isOk());
    }

    @Test
    public void getProducts() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .request(HttpMethod.GET, "/api/product/")
                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
    }


}