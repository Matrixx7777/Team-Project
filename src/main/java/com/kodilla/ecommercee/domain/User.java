package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    private Long id;

    @OneToMany(targetEntity = Cart.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Cart> carts;
}
