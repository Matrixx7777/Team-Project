package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartMapper {

    private ProductMapper productMapper;
    private UserMapper userMapper;

    public CartDto mapToCartDto(Cart cart) {

        List<ProductDto> productDtoList = productMapper.mapToProductDtoList(cart.getProducts());

        return new CartDto(
                cart.getId(),
                userMapper.mapToUserDto(cart.getUser()),
                productDtoList);
    }
}
