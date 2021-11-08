package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.status.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private CartDbService cartDbService;
    private CartMapper cartMapper;
    private ProductMapper productMapper;
    private OrderMapper orderMapper;

    @PostMapping("createCart")
    public CartDto createCart(@RequestParam Long userId) throws UserNotFoundException {
        User user = cartDbService.getUser(userId).orElseThrow(UserNotFoundException::new);
        Cart cart = new Cart(user);
        cartDbService.saveCart(cart);
        return cartMapper.mapToCartDto(cart);
    }

    @GetMapping("getProductsFromCart")
    public List<ProductDto> getProductsFromCart(@RequestParam Long cartId) throws CartNotFoundException {
            Cart cart = getCart(cartId);
            List<Product> productsInCart = cart.getProducts();
            return productMapper.mapToProductDtoList(productsInCart);
    }

    @PutMapping("addProductToCart")
    public void addProductToCart(@RequestParam Long cartId, @RequestParam Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = getCart(cartId);
        Product product = getProduct(productId);

        cart.getProducts().add(product);
        product.getCarts().add(cart);

        cartDbService.saveCart(cart);
        cartDbService.saveProduct(product);
    }

    @DeleteMapping("deleteProductFromCart")
    public void deleteProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) throws CartNotFoundException, ProductNotFoundException{
        Cart cart = getCart(cartId);
        Product product = getProduct(productId);

        if (cart.getProducts().contains(product)) {
            cart.getProducts().remove(product);
            product.getCarts().remove(cart);
        } else {
            System.out.println("There is no such a product in Cart");
        }
        cartDbService.saveCart(cart);
        cartDbService.saveProduct(product);
    }

    @PostMapping("makeOrder")
    public OrderDto makeOrder(@RequestParam Long cartId) throws CartNotFoundException {
        Cart cart = getCart(cartId);
        Order order = new Order(cart,OrderStatus.CREATED);
        cartDbService.saveOrder(order);
        return orderMapper.mapToOrderDto(order);
    }


    public Cart getCart(Long cartId) throws CartNotFoundException {
        return cartDbService.getCart(cartId).orElseThrow(CartNotFoundException::new);
    }

    public Product getProduct(Long productId) throws ProductNotFoundException {
        return cartDbService.getProduct(productId).orElseThrow(ProductNotFoundException::new);
    }
}
