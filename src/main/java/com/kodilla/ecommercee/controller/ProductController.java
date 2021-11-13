package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/product")
public class ProductController {

    private final ProductDbService productDbService;
    private final ProductMapper productMapper;

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        List<Product> products = productDbService.getAllProducts();
        return productMapper.mapToProductDtoList(products);
    }

    @GetMapping(value = "getProduct")
    public ProductDto getProduct (@RequestParam Long productId) throws ProductNotFoundException {
        return productMapper.mapToProductDto(
            productDbService.getProduct(productId).orElseThrow(ProductNotFoundException::new)
        );
    }

    @PostMapping(value = "createProduct")
    public ProductDto createProduct(@RequestBody ProductDto productDto) throws GroupNotFoundException {
        Product product = productMapper.mapToProduct(productDto);
        Product savedProduct = productDbService.saveProduct(product);
        return productMapper.mapToProductDto(savedProduct);
    }

    @PutMapping(value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) throws GroupNotFoundException, ProductNotFoundException {
        Product product = productMapper.mapToProduct(productDto);
        Product updatedProduct = productDbService.updateProduct(product);
        return productMapper.mapToProductDto(updatedProduct);
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId) throws ProductNotFoundException {
        productDbService.deleteProduct(productId);
    }
}
