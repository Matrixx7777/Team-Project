package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartDbService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Optional<Cart> getCart(final Long cartId) {
        return cartRepository.findById(cartId);
    }
    public Cart saveCart(final Cart cart) {
        return cartRepository.save(cart);
    }

    public Order saveOrder(final Order order) {
        return orderRepository.save(order);
    }

    public Optional<Product> getProduct(final Long productId) {
        return productRepository.findById(productId);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }
}
