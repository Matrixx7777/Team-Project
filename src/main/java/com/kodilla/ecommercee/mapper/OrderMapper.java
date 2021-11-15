package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final CartRepository cartRepository;

    public Order mapToOrder(OrderDto orderDto) throws CartNotFoundException {
        Cart cart = cartRepository.findById(orderDto.getCartDtoId()).orElseThrow(CartNotFoundException::new);
        return new Order(cart,orderDto.getOrderStatus());
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCart().getId(),
                order.getStatus()
        );
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orders) {
        return orders.stream().map(this::mapToOrderDto).collect(Collectors.toList());
    }
}

