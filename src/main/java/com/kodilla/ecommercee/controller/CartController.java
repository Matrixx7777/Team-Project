package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.dto.UserDto;;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.OrderDbService;
import com.kodilla.ecommercee.service.ProductDbService;
import com.kodilla.ecommercee.status.OrderStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private CartDbService cartDbService;
    private ProductDbService productDbService;
    private OrderDbService orderDbService;
    private CartMapper cartMapper;
    private ProductMapper productMapper;
    private UserMapper userMapper;

    @PostMapping("createCart")
    public CartDto createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        Cart savedCart = cartDbService.saveCart(cart);
        return cartMapper.mapToCartDto(savedCart);
    }

    @GetMapping("getCarts")
    public List<ProductDto> getProductsFromCart(@RequestParam long cartId) throws CartNotFoundException {
            Cart cart = getCart(cartId);
            List<Product> productsInCart = cart.getProducts();
            return productMapper.mapToProductDtoList(productsInCart);
    }

    @PostMapping("addProductToCart")
    public void addProductToCart(@RequestParam long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = getCart(cartId);
        Product product = getProduct(productId);

        cart.getProducts().add(product);
        product.getCarts().add(cart);

        cartDbService.saveCart(cart);
        productDbService.saveProduct(product);
    }

    @DeleteMapping("deleteProductFromCart")
    public void deleteProductFromCart(@RequestParam long cartId, @RequestParam long productId) throws CartNotFoundException, ProductNotFoundException{
        Cart cart = getCart(cartId);
        Product product = getProduct(productId);

        if (cart.getProducts().contains(product)) {
            cart.getProducts().remove(product);
            product.getCarts().remove(cart);
        } else {
            System.out.println("There is no such a product in Cart");
        }
        cartDbService.saveCart(cart);
        productDbService.saveProduct(product);
    }

    @PostMapping("makeOrder")
    public OrderDto makeOrder(@RequestParam long cartId) throws CartNotFoundException {
        Cart cart = getCart(cartId);
        Order order = new Order(cart,OrderStatus.CREATED);
        orderDbService.saveOrder(order);
        return orderMapper.mapToOrderDto(order);
    }


    public Cart getCart(Long cartId) throws CartNotFoundException {
        return cartDbService.getCart(cartId).orElseThrow(CartNotFoundException::new);
    }

    public Product getProduct(Long productId) throws ProductNotFoundException {
        return productDbService.getProduct(productId).orElseThrow(ProductNotFoundException::new);
    }
}
