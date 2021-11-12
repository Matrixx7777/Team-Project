package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.status.OrderStatus;
import lombok.Getter;

@Getter
public class OrderDto {

    private Long id;
    private CartDto cartDto;
    private OrderStatus orderStatus;

    public OrderDto(Long id, CartDto cartDto, OrderStatus orderStatus) {
        this.id = id;
        this.cartDto = cartDto;
        this.orderStatus = orderStatus;
    }

}
