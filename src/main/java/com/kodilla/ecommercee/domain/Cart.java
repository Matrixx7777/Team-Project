
package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CARTS")
public class Cart {
    private Long id;
    private User user;
    private Order order;
    private List<Product> products = new ArrayList<>();


    public Cart(User user, Order order) {
        this.user = user;
        this.order = order;
    }

    public Cart() {
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="CART_ID", unique = true)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //doprecyzowac
    @JoinColumn(name = "ORDER_ID")
    public Order getOrder() {
        return order;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name= "CARTS_HAS_PRODUCTS",
            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")}
    )
    public List<Product> getProducts() {
        return products;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setUser(User user) {
        this.user = user;
    }

    private void setOrder(Order order) {
        this.order = order;
    }

    private void setProducts(List<Product> products) {
        this.products = products;
    }
}

