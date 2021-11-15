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
        Product product = new Product("Test product","Test product desc", 24.99,group);
        //When
        productRepository.save(product);
        //Then
        int numberOfGroups = groupRepository.findAll().size();
        int numberOfProducts = productRepository.findAll().size();
        assertEquals(1,numberOfGroups);
        assertEquals(1,numberOfProducts);
        //CleanUp
        Product productToDelete = productRepository.findAll().get(0);
        productToDelete.setGroup(null);
        productRepository.save(productToDelete);
        productRepository.deleteAll();
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

        int cartsInProductBeforeAdding = savedProduct.getCarts().size();
        savedProduct.getCarts().add(cart);
        cart.getProducts().add(savedProduct);
        productRepository.save(savedProduct);

        Product productWithAddedCart = productRepository.findById(id).get();
        int cartsInProductAfterSaving = productWithAddedCart.getCarts().size();

        //Then
        assertEquals(cartsInProductBeforeAdding + 1, cartsInProductAfterSaving);

        //CleanUp
        Product productToDelete= productRepository.findAll().get(0);
        productToDelete.setGroup(null);
        productToDelete.getCarts().remove(0);
        Cart cartToDelete = cartRepository.findAll().get(0);
        cartToDelete.getProducts().remove(0);
        productRepository.save(productToDelete);
        cartRepository.save(cartToDelete);
        productRepository.deleteAll();
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
        userRepository.save(user);
        productRepository.save(product);
        int numbersOfProducts = productRepository.findAll().size();
        int numbersOfCarts = cartRepository.findAll().size();
        int numberOfUsers = userRepository.findAll().size();
        int numbersOfGroups = groupRepository.findAll().size();

        //When
        Cart cartFromRepo = cartRepository.findAll().get(0);
        cartFromRepo.getProducts().remove(0);
        product.setGroup(null);
        productRepository.save(product);
        cartRepository.save(cartFromRepo);
        productRepository.deleteAll();

        //Then
        int numbersOfProductsAfterDeleting = productRepository.findAll().size();
        int numbersOfCartsAfterDeleting = cartRepository.findAll().size();
        int numberOfUsersAfterDeleting = userRepository.findAll().size();
        int numbersOfGroupsAfterDeleting = groupRepository.findAll().size();

        assertEquals(numbersOfProducts - 1, numbersOfProductsAfterDeleting);
        assertEquals(numbersOfCarts, numbersOfCartsAfterDeleting);
        assertEquals(numberOfUsers, numberOfUsersAfterDeleting);
        assertEquals(numbersOfGroups,numbersOfGroupsAfterDeleting);

    }

    @Test
    public void updateProduct() {
        //Given
        Group group = new Group("Test group");
        Product product = new Product("Test product","Test product desc", 24.99, group);
        productRepository.save(product);
        //Then
        Product savedProduct = productRepository.findAll().get(0);
        savedProduct.setPrice(9.99);
        savedProduct.setDescription("New description");
        savedProduct.setName("New name");
        productRepository.save(savedProduct);
        //Then
        Product updatedProduct = productRepository.findAll().get(0);
        assertEquals(9.99, updatedProduct.getPrice());
        assertEquals("New name", updatedProduct.getName());
        assertEquals("New description", updatedProduct.getDescription());
    }

}
