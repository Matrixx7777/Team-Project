package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Getter
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products", fetch = FetchType.LAZY)
    @Column(name = "ITEM_ID")
    private List<Cart> carts = new ArrayList<>();

    @ManyToOne
    private Group group;
}
