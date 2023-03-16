package com.example.springMicroservices.orderService.controller;

import com.example.springMicroservices.orderService.dto.requestDto.OrderRequest;
import com.example.springMicroservices.orderService.dto.responceDto.OrderResponse;
import com.example.springMicroservices.orderService.repository.OrderRepository;
import com.example.springMicroservices.orderService.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.http.HttpRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class OrderControllerTest {
    @Container
    private MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Test
    public void placeOrder() throws Exception {
        OrderRequest orderRequest = Utils.getOrderRequest();
        OrderResponse expectedResponse = Utils.getOrderResponce();
        String orderRequestContent = objectMapper.writeValueAsString(orderRequest);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                             .request(HttpMethod.POST, "/api/order/")
                                             .contentType(MediaType.APPLICATION_JSON)
                                             .content(orderRequestContent))
                                     .andExpect(status().isCreated())
                                     .andReturn();
        String actualOrderResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualOrderResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(expectedResponse));
    }


}