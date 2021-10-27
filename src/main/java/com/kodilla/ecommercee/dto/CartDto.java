package com.kodilla.ecommercee.dto;

import java.util.List;

public class CartDto {

    private Long id;
    private UserDto userDto;
    private List<ProductDto> products;

    public CartDto(Long id, UserDto userDto, List<ProductDto> products) {
        this.id = id;
        this.userDto = userDto;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void addProduct(ProductDto productDto) {
        products.add(productDto);
    }
}
