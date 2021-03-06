package com.kodilla.ecommercee.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="CARTS")
public class Cart {

    @Id
    @GeneratedValue
    @Column(name="CART_ID", unique = true)
    private Long id;


    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @NotNull
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name= "CARTS_HAS_PRODUCTS",
            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")}
    )
    private List<Product> products = new ArrayList<>();

    public Cart(User user) {
        this.user = user;
    }

    public Cart() {
    }

    public Long getId() {
        return id;
    }
}