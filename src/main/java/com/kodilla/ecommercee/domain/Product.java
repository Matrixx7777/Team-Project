package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private List<Cart> carts = new ArrayList<>();


}
