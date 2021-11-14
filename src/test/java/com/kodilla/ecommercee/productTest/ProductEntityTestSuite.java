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
        //Then
        int productsAfterDeleting = productRepository.findAll().size();
        int groupsAfterDeletingProduct = groupRepository.findAll().size();
        assertEquals(0,productsAfterDeleting);
        assertEquals(1,groupsAfterDeletingProduct);

    }

    @Test
    public void testAddProductToCart() {

        //Given
        Group group = new Group("Test group");
        Product product = new Product("Test product","Test product desc", 24.99, group);
        User user = new User("Jan","Nowak");
        Cart cart = new Cart(user);
        productRepository.save(product);

        int cartsBeforeSaving = cartRepository.findAll().size();
        int productsBeforeSaving = productRepository.findAll().size();

        //When
        List<Product> products = productRepository.findAll();
        Product savedProduct = products.get(0);
        Long id = savedProduct.getId();

        savedProduct.getCarts().add(cart);
        cart.getProducts().add(savedProduct);

        productRepository.save(savedProduct);
        Product productWithAddedCart = productRepository.findById(id).get();
        System.out.println(productWithAddedCart.getCarts());


        //Then
        int numberOfCarts = cartRepository.findAll().size();
        int numberOfProducts = productRepository.findAll().size();
        assertEquals(1,numberOfCarts);
        assertEquals(1,numberOfProducts);

        //Co tu sprawdzić?
        //czy ilość cartów w produkcie się zwiększyła
        //czy ilość cartów się zwiększyła
        //czy ilość produktów się zwiększyła?


        //Cleanup
        //productRepository.deleteAll(); //carta nie usuwa

        //Then



    }


    @Test
    public void testRemoveProduct() {


    //czy spowoduje usunięcie grupy i cartów?
        //powinno tylko spowodować usunięcie z listy produków. Lista cartów i grup powinna zostać nie zmieniona

        /*int cartsAfterDeletingProduct = cartRepository.findAll().size();
        int groupsAfterDeletingProduct = groupRepository.findAll().size();
        int usersAfterDeletingProduct = userRepository.findAll().size();
        assertEquals(1,cartsAfterDeletingProduct);
        assertEquals(1,groupsAfterDeletingProduct);//klasycznie usuwa grupe
        assertEquals(1,usersAfterDeletingProduct);*/
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
