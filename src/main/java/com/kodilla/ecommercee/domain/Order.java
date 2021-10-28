package com.kodilla.ecommercee.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Order {

    @Id
    private Long id;

    @OneToOne
    private Cart cart;

}
