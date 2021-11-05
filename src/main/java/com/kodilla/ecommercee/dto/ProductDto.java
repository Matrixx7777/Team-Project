package com.kodilla.ecommercee.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int groupId;

    
    public ProductDto(Long id) {
        this.id = id;
    }
}



