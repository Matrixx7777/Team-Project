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
public class CartDbService {

    private final CartRepository cartRepository;

    public Optional<Cart> getCart(final Long cartId) {
        return cartRepository.findById(cartId);
    }
    public Cart saveCart(final Cart cart) {
        return cartRepository.save(cart);
    }
}
