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
@RequestMapping("/v1/cart")
public class CartController {

    @PostMapping("createCart")
    public CartDto createCart(@RequestBody CartDto cartDto) {
        return cartDto;
    }

    @GetMapping("getCarts")
    public List<ProductDto> getCartProducts(@RequestParam long cartId) {
        CartDto cartDto = new CartDto(1L, new UserDto(1L,"John","Smith"), new ArrayList<>());
        return cartDto.getProducts();
    }

    @PostMapping("addProductToCart")
    public void addProductToCart(@RequestParam long cartId, @RequestBody ProductDto productDto) { //tu poprawiłem

        //obiekt Cart ma listę i do niego dodamy po prostu produkt z argumentu
    }

    @DeleteMapping("deleteCart")
    public void deleteProduct(@RequestParam long cartId, @RequestParam String productName, @RequestParam int quantity) {
        //pętlą
        //sprawdź ile jest produktów. jeśli wiecej niż quantity zwróc error, że
        //wyszukaj koszyk
        //wyszukaj w koszyku produkt po nazwie
        //usun pierwszy napotkany produkt

    }

    @PostMapping("makeOrder")
    public OrderDto makeOrder(@RequestParam long cartId) {
        CartDto cartDto = new CartDto(1L, new UserDto(1L,"John","Smith"), new ArrayList<>());
        return new OrderDto(1L,1, OrderStatus.CREATED.name());
    }
}
