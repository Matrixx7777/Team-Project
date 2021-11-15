package com.kodilla.ecommercee.productTest;


import com.kodilla.ecommercee.controller.ProductController;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import static org.junit.jupiter.api.Assertions.*;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEntityTestSuite {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddProduct() {

        //Given
        Group group = new Group("Test group");
        Product product = new Product("Test product","Test product desc", 24.99, group);
        //When
        productRepository.save(product);
        //Then
        int numberOfGroups = groupRepository.findAll().size();
        int numberOfProducts = productRepository.findAll().size();
        assertEquals(numberOfGroups,1);
        assertEquals(numberOfProducts,1);
        //Cleanup
        productRepository.deleteAll(); // usuwa też grupę, a nie powinien
        groupRepository.deleteAll();

    }

    @Test
    public void testAddProductToCart() {

        //Given
        Group group = new Group("Test group");
        Product product = new Product("Test product","Test product desc", 24.99, group);
        User user = new User("Jan","Nowak");
        Cart cart = new Cart(user);
        productRepository.save(product);


        //When
        List<Product> products = productRepository.findAll();
        Product savedProduct = products.get(0);
        Long id = savedProduct.getId();

        int cartsInProductBeforeSaving = savedProduct.getCarts().size();
        savedProduct.getCarts().add(cart);
        cart.getProducts().add(savedProduct);
        productRepository.save(savedProduct);
        Product productWithAddedCart = productRepository.findById(id).get();
        int cartsInProductAfterSaving = productWithAddedCart.getCarts().size();

        //Then
        int numberOfCarts = cartRepository.findAll().size();
        int numberOfProducts = productRepository.findAll().size();
        assertEquals(1,numberOfCarts);
        assertEquals(1,numberOfProducts);
        assertEquals(cartsInProductBeforeSaving + 1, cartsInProductAfterSaving);

        //Cleanup
        productRepository.deleteAll(); //carta nie usuwa
        cartRepository.deleteAll();
        groupRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    public void testDeleteProduct() {

        //Given
        Group group = new Group("Test group");
        Product product = new Product("Test product","Test product desc", 24.99, group);
        User user = new User("Jan","Nowak");
        Cart cart = new Cart(user);
        product.getCarts().add(cart);
        cart.getProducts().add(product);
        productRepository.save(product);
        int numbersOfProducts = productRepository.findAll().size();
        int numbersOfCarts = cartRepository.findAll().size();
        int numberOfUsers = userRepository.findAll().size();
        int numbersOfGroups = groupRepository.findAll().size();

        System.out.println("products " + numbersOfProducts);
        System.out.println("carts " + numbersOfCarts);
        System.out.println("users " + numberOfUsers);
        System.out.println("groups " + numbersOfGroups);

        //When
        productRepository.deleteById(1L);


        //Then
        int numbersOfProductsAfterDeleting = productRepository.findAll().size();
        int numbersOfCartsAfterDeleting = cartRepository.findAll().size();
        int numberOfUsersAfterDeleting = userRepository.findAll().size();
        int numbersOfGroupsAfterDeleting = groupRepository.findAll().size();

        assertEquals(numbersOfProducts - 1, numbersOfProductsAfterDeleting);
        //assertEquals(numbersOfCarts, numbersOfCartsAfterDeleting); //usuwa carta
        //assertEquals(numberOfUsers, numberOfUsersAfterDeleting); //usuwa usera
        assertEquals(numbersOfGroups,numbersOfGroupsAfterDeleting);

    }

    @Test
    public void testRemoveProductFromGroup() {
        //czy usunie grupę? nie powinno
    }

    @Test
    public void testRemoveProductFromCart() {
        //czy usunie cały koszyk?
    }

}
