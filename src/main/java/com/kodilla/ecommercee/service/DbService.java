package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Optional<Cart> getCart(final Long cartId) {
        return cartRepository.findById(cartId);
    }

    public Optional<Product> getProduct(final Long productId) {
        return productRepository.findById(productId);
    }
}
