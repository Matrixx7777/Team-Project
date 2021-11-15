package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTestSuite {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateNewCard(){
        //Given
        Product product = new Product(1L,"Laptop", "Good params", 8000, new Group("Electro"));
        User user = new User("TestName", "TestSurname");
        Cart cart = new Cart(user);

        //When
        Product saveProduct = productRepository.save(product);
        userRepository.save(user);
        Cart saveCart = cartRepository.save(cart);

        cart.getProducts().add(product);

        long productId = saveProduct.getId();
        long cartId = saveCart.getId();

        //Then
        assertThat(cartId).isGreaterThan(0);
        assertThat(saveCart.getProducts()).isNotNull();
        assertThat(saveCart.getUser()).isNotNull();

        //Delete
        productRepository.deleteById(productId);
        cartRepository.deleteById(cartId);
    }

    @Test
    public void testUpdateCart() {
        // Given
        Group cars = new Group("Cars");
        Product product = new Product(1L,"Ferrari", "400km/h", 1320000, cars);
        User user = new User("Ghost","Rider");
        Cart cart = new Cart(user);

        // When
        userRepository.save(user);
        Cart saveCart = cartRepository.save(cart);
        cart.getProducts().add(product);
        long cartId = saveCart.getId();

        // Then
        assertThat(cartId).isGreaterThan(0);
        assertThat(cartId).isNotNull();

        //Delete
        cartRepository.deleteById(cartId);
    }

    @Test
    public void testGetCards() {
        // Given
        User user1 = new User("TestName1","TestSurname1");
        Cart firstCart = new Cart(user1);
        User user2 = new User("TestName2","TestSurname2");
        Cart secondCart = new Cart(user2);
        User user3 = new User("TestName3","TestSurname3");
        Cart thirdCart = new Cart(user3);

        // When
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        cartRepository.save(firstCart);
        cartRepository.save(secondCart);
        cartRepository.save(thirdCart);

        long cartIdOne = firstCart.getId() - 3;
        long cartIdTwo = secondCart.getId() - 3;
        long cartIdThree = thirdCart.getId() - 3;

        Iterable<Cart> cartList = cartRepository.findAll();

        // Then
        assertThat(cartList).isNotNull();
        assertEquals(cartIdOne,1);
        assertEquals(cartIdTwo,2);
        assertEquals(cartIdThree,3);

        //Delete
        cartRepository.deleteById(firstCart.getId());
        cartRepository.deleteById(secondCart.getId());
        cartRepository.deleteById(thirdCart.getId());
    }

    @Test
    public void testGetCard() {
        // Given
        User user = new User("Ghost","Rider");
        Cart cart = new Cart(user);

        // When
        userRepository.save(user);
        cartRepository.save(cart);
        long userId = user.getId();
        long cartId = cart.getId() - 1;

        Optional<Cart> cartIn = cartRepository.findById(cartId);

        // Then
        assertThat(cartIn).isNotNull();
        assertThat(cart.getId()).isGreaterThan(0);
        assertEquals(cartId,1);

        //Delete
        userRepository.deleteById(userId);
    }

    @Test
    public void testDeleteCart() {
        // Given
        User user = new User("Ghost","Rider");
        Cart cart = new Cart(user);

        // When
        userRepository.save(user);
        cartRepository.save(cart);
        long cartId = cart.getId();
        cartRepository.deleteById(cartId);

        // Then
        boolean cartExists = cartRepository.existsById(cartId);

        assertThat(cartExists).isEqualTo(false);
    }
}