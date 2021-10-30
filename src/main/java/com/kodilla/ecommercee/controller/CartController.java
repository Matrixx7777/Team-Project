package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.dto.UserDto;;
import com.kodilla.ecommercee.status.OrderStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @PostMapping
    public CartDto createCart(@RequestParam long userId) {
        return new CartDto(1L, new UserDto(userId,"John","Smith"), new ArrayList<>());
    }

    @GetMapping
    public List<ProductDto> getCartProducts(@RequestParam long cartId) {
        CartDto cartDto = new CartDto(1L, new UserDto(1L,"John","Smith"), new ArrayList<>());
        return cartDto.getProducts();
    }

    @PostMapping("/add")
    public CartDto addProductToCart(@RequestParam long cartId, @RequestParam List<Long> productsId) {
        CartDto cartDto = new CartDto(1L, new UserDto(1L,"John","Smith"), new ArrayList<>());
        for (Long id : productsId) {
            cartDto.addProduct(new ProductDto(id,"Name" + id,"Desc" + id,9.99,1));
        }
        return cartDto;
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam long cartId, @RequestParam long productId) {
        CartDto cartDto = new CartDto(1L, new UserDto(1L,"John","Smith"), new ArrayList<>());
        cartDto.getProducts().removeIf(product -> product.getId().equals(BigInteger.valueOf(productId)));
    }

    @PostMapping("/makeOrder")
    public OrderDto makeOrder(@RequestParam long cartId) {
        CartDto cartDto = new CartDto(1L, new UserDto(1L,"John","Smith"), new ArrayList<>());
        return new OrderDto(1L,1, OrderStatus.CREATED.name());
    }
}
