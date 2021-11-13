package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderMapper {

    private CartMapper cartMapper;

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                cartMapper.mapToCartDto(order.getCart()),
                order.getStatus()
        );
    }
}
