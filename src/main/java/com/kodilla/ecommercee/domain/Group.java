package com.kodilla.ecommercee.domain;

import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "PRODUCT_GROUPS")
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "GROUP_ID")
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(targetEntity = Product.class,
            mappedBy = "group",cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    private List<Product> productList;


    public Group(String name) { this.name = name; this.productList = new ArrayList(); }
}

