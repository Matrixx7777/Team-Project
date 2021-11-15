package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.status.OrderStatus;
import lombok.Getter;

@Getter
public class OrderDto {

    private Long id;
    private Long cartDtoId;
    private OrderStatus orderStatus;

    public OrderDto(Long id, Long cartDtoId, OrderStatus orderStatus) {
        this.id = id;
        this.cartDtoId = cartDtoId;
        this.orderStatus = orderStatus;
    }

}
