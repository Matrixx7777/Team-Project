package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testCreateUser() {

        // Given
        User user = new User("Ambroży", "Kleks");

        // When
        User savedUser = userRepository.save(user);
        long userId = savedUser.getId();

        // Then
        assertThat(userId).isGreaterThan(0);

        try {
            userRepository.deleteById(savedUser.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testRetrieveUser() {

        // Given
        User user = new User("Ambroży", "Kleks");
        User savedUser = userRepository.save(user);
        long userId = savedUser.getId();

        // When
        User retrivedUser = userRepository.findById(userId).get();

        // Then
        assertThat(retrivedUser.getId()).isGreaterThan(0);

        try {
            userRepository.deleteById(savedUser.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testRetrieveUsers() {

        // Given
        User user1 = new User( "Ambroży", "Kleks");
        User user2 = new User( "Jan", "Kowalski");
        User user3 = new User( "Krzysztof", "Nowak");
        userRepository.save(user1).getId();
        userRepository.save(user2).getId();
        userRepository.save(user3);

        // When
        List<User> retrivedUsers = userRepository.findAll();

        // Then
        assertEquals(3, retrivedUsers.size());

        try {
            userRepository.deleteById(user1.getId());
            userRepository.deleteById(user2.getId());
            userRepository.deleteById(user3.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testUpdateUser() {

        // Given
        User user = new User( "Ambroży", "Kleks");
        User savedUser = userRepository.save(user);

        // When
        savedUser.setLastName("Kowalski");
        User retrivedUser = userRepository.save(savedUser);

        // Then
        assertEquals("Kowalski", retrivedUser.getLastName());

        try {
            userRepository.deleteById(savedUser.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testDeleteUserShouldBeDeleted() {

        // Given
        User user = new User( "Ambroży", "Kleks");
        User savedUser = userRepository.save(user);
        Cart cart = new Cart(savedUser);
        cartRepository.save(cart);
        long cartId = cart.getId();

        // When
        try {
            userRepository.deleteById(savedUser.getId());
        } catch (Exception e) {
            //do nothing
        }

        // Then
        assertThat(cartRepository.existsById(cartId)).isEqualTo(false);

    }

}

