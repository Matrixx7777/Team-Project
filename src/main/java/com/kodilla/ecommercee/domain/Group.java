package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Getter
@Table(name = "GROUPS")
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "GROUP_ID")
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(targetEntity = Product.class, mappedBy = "group",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> productList;

    public Group(String name) {
        this.name = name;
        this.productList = new ArrayList<>();
    }
}
