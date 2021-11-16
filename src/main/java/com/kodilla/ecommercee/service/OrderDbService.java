package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.status.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDbService {

    private final OrderRepository orderRepository;

    public Order getOrder(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public List<Order> getOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, OrderStatus status) {
        Order orderDb = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        orderDb.setStatus(status);
        return orderRepository.save(orderDb);
    }

    public void deleteOrder(long orderId) {
        if (!orderRepository.existsById(orderId))
            throw new OrderNotFoundException();
        orderRepository.deleteById(orderId);
    }
}

