package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/order")
public class OrderController {

    @GetMapping(value = "getOrders")
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping(value = "getOrder")
    public void getOrder (@RequestParam int orderId) {
        //return new OrderDto(1L, 1, "new status");
    }

    @PostMapping(value = "createOrder")
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderDto;
    }

    @PutMapping(value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderDto;
    }

    @DeleteMapping(value = "deleteOrder")
    public void deleteOrder(@RequestParam int orderId) {
        //do nothing
    }
}
