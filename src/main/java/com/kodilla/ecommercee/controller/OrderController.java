package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDbService orderDbService;
    private final OrderMapper orderMapper;

    @GetMapping
    public List<OrderDto> getOrders() {
        List<Order> orders = orderDbService.getOrders();
        return orderMapper.mapToOrderDtoList(orders);
    }

    @GetMapping("/{id}")
    public OrderDto getOrder (@PathVariable("id") long orderId) {
        Order order = orderDbService.getOrder(orderId);
        return orderMapper.mapToOrderDto(order);
    }

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderDto orderDto) throws CartNotFoundException {
        Order order = orderMapper.mapToOrder(orderDto);
        Order savedOrder = orderDbService.saveOrder(order);
        return orderMapper.mapToOrderDto(savedOrder);
    }

    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        Order updatedOrder = orderDbService.updateOrder(orderDto.getId(),orderDto.getOrderStatus());
        return orderMapper.mapToOrderDto(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") long orderId) {
        orderDbService.deleteOrder(orderId);
    }
}