package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.dto.UserDto;;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.DbService;
import com.kodilla.ecommercee.status.OrderStatus;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private DbService service;
    private CartMapper cartMapper;
    private ProductMapper productMapper;

    @PostMapping("createCart")
    public CartDto createCart(@RequestBody CartDto cartDto) {
        return cartDto;
    }

    @GetMapping("getCarts")
    public List<ProductDto> getProductsFromCart (@RequestParam long cartId) throws CartNotFoundException {
            Cart cart = getCart(cartId);
            List<Product> productsInCart = cart.getProducts();
            return productMapper.mapToProductDtoList(productsInCart);
    }

    @PostMapping("addProductToCart")
    public void addProductToCart(@RequestParam long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException { //tu poprawiłem
        Cart cart = getCart(cartId);
        Product product = getProduct(productId);
        cart.getProducts().add(product);
        product.getCarts().add(cart);

        //koniec + zapisz zmiany?

    }

    @DeleteMapping("deleteProductFromCart")
    public void deleteProductFromCart(@RequestParam long cartId, @RequestParam long productId, @RequestParam int quantity) throws CartNotFoundException, ProductNotFoundException{
        Cart cart = getCart(cartId);
        Product product = getProduct(productId)
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

    public Cart getCart(Long cartId) throws CartNotFoundException {
        return service.getCart(cartId).orElseThrow(CartNotFoundException::new);
    }

    public Product getProduct(Long productId) throws ProductNotFoundException {
        return service.getProduct(productId).orElseThrow(ProductNotFoundException::new);
    }
}
