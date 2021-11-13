package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Entity
@Getter
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;


    @Column(name = "name")
    private String firstName;

    @Column(name = "surname")
    private String lastName;


    @Column(name = "carts")
    private List<Cart> cartList;

    public User(Long id, String firstName, String lastName, List<Cart> cartList){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cartList = cartList;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    @OneToMany(
            targetEntity = Cart.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Cart> getCartList(){ return cartList; }

}
