package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID", unique=true)
    private Long id;

    @Column(name = "name")
    private  String firstName;

    @Column(name = "surname")
    private  String lastName;

//    @OneToMany(
//            targetEntity = Cart.class,
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
    public Long getId(){
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
