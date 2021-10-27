package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "GROUPS")
public class Group {

    private int id;
    private String name;
//    private Product product;

    public Group() {

    }

    public Group(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

//    @ManyToOne
//    @JoinColumn(name = "PRODUCT_ID")
//    public Product getGroupOfProducts() { return product; }
//
//    public void setGroupOfProducts(Product product) {
//        this.product = product;
//    }

}
