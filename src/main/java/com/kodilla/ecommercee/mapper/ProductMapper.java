package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.service.GroupDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductMapper {

    private final GroupDbService groupDbService;

    public Product mapToProduct(final ProductDto productDto) throws GroupNotFoundException {
        Group group = groupDbService.getGroup(productDto.getGroupId()).orElseThrow(GroupNotFoundException::new);
        return new Product(
            productDto.getId(),
            productDto.getName(),
            productDto.getDescription(),
            productDto.getPrice(),
            group
        );
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getGroup().getId()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> products) {
        return products.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
