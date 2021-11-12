package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.Cart;
import lombok.Getter;


import java.util.List;

@Getter
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private List<CartDto> cartDtoList;

    public UserDto(Long id, String firstName, String lastName, List<CartDto> cartDtoList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cartDtoList = cartDtoList;
    }

    public UserDto(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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



}
