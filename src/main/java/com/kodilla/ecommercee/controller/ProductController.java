package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/product")
public class ProductController {

    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }

    @GetMapping(value = "getProduct")
    public ProductDto getProduct (@RequestParam int productId) {
        return new ProductDto(1L); /*,"Test product","Test product description",9.99,1);*/
    }

    @PostMapping(value = "createProduct")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @PutMapping(value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam int productId) {
    }
}
