package com.kodilla.ecommercee.domain;

import lombok.Getter;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "PRODUCTS")
public class Product {

    private Long id;
    private String name;
    private String description;
    private double price;
    private Group group;
    private List<Cart> carts = new ArrayList<>();

    public Product() {    }

    public Product(String name, String description, double price, Group group) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "PRODUCT_ID", unique = true)
    public Long getId() {
        return id;
    }

    @NotNull
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @NotNull
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @NotNull
    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }


    @ManyToOne(cascade = {CascadeType.ALL/*PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE*/})
    @JoinColumn(name = "GROUP_ID")
    public Group getGroup() {
        return group;
    }

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
            mappedBy = "products",
            fetch = FetchType.EAGER)
    public List<Cart> getCarts() {
        return carts;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

}
