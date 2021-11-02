package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDbService {

    private final ProductRepository productRepository;

    public Optional<Product> getProduct(final Long productId) {
        return productRepository.findById(productId);
    }

    public List<Product> findProductInCart(final Long productId, final Long cartId) { //zbędne?
        return productRepository.findByIdAndCart(productId,cartId);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }
}
