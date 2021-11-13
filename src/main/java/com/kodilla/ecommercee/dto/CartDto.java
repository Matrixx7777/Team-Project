package com.kodilla.ecommercee.dto;


import lombok.NoArgsConstructor;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter

public class CartDto {

    private Long id;
    private UserDto userDto;
    private List<ProductDto> products;

}
