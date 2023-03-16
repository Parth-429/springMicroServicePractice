package com.example.springMicroservices.orderService.controller;

import com.example.springMicroservices.orderService.dto.requestDto.OrderRequest;
import com.example.springMicroservices.orderService.dto.responceDto.OrderResponse;
import com.example.springMicroservices.orderService.model.Order;
import com.example.springMicroservices.orderService.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.CompletableFuture;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/order/")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @CircuitBreaker(name="inventory", fallbackMethod = "fallBackMethodForOrder")
    @TimeLimiter(name = "inventory")
    @ResponseStatus(HttpStatus.CREATED)
    @Retry(name="inventory")
    public CompletableFuture<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));
    }

    public CompletableFuture<String> fallBackMethodForOrder(OrderRequest orderRequest, RuntimeException runtimeException){
         return CompletableFuture.supplyAsync(()->"Oops!Something wents wrong please wait for some time and " +
                                                         "try again later");
    }
}
