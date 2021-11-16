package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CartRepository extends CrudRepository<Cart,Long> {

    @Override
    List<Cart> findAll();


    int findAllByProducts(List<Product> productList);
}
