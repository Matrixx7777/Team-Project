package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.status.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(Cart cart, OrderStatus status) {
        this.cart = cart;
        this.status = status;
    }
}
