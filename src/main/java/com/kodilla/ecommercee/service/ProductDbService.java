package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDbService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(final Long productId) {
        return productRepository.findById(productId);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(final Product product) throws ProductNotFoundException {
        if (productRepository.existsById(product.getId())) {
            Product updatedProduct = productRepository.findById(product.getId()).orElse(product);
            updatedProduct.setName(product.getName());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setGroup(product.getGroup());
            return productRepository.save(updatedProduct);
        } else {
            throw new ProductNotFoundException();
        }
    }

    public void deleteProduct(final Long productId) throws ProductNotFoundException {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new ProductNotFoundException();
        }
    }

}
