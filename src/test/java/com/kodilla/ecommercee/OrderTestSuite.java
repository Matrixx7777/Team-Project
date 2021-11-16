package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.status.OrderStatus;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testCreateNewOrder() {
        // Given
        User user = new User("John","Smith");
        User savedUser = userRepository.save(user);
        Cart cart = new Cart(user);
        Order order = new Order(cart, OrderStatus.CREATED);
        // When
        Order savedOrder = orderRepository.save(order);
        // Then
        long id = savedOrder.getId();
        assertThat(id).isGreaterThan(0);
        assertThat(savedOrder.getCart()).isNotNull();

        orderRepository.deleteById(id);
        userRepository.deleteById(savedUser.getId());
    }

    @Test
    public void testRetrieveAllOrders() {
        // Given
        User user = new User("John","Smith");
        User savedUser = userRepository.save(user);
        Cart cart1 = new Cart(user);
        Cart cart2 = new Cart(user);
        Cart cart3 = new Cart(user);
        Order order1 = new Order(cart1, OrderStatus.CREATED);
        Order order2 = new Order(cart2, OrderStatus.CREATED);
        Order order3 = new Order(cart3, OrderStatus.CREATED);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        // When
        List<Order> savedOrders = (List<Order>) orderRepository.findAll();
        // Then
        assertEquals(3,savedOrders.size());

        orderRepository.deleteById(order1.getId());
        orderRepository.deleteById(order2.getId());
        orderRepository.deleteById(order3.getId());
        userRepository.deleteById(savedUser.getId());
    }

    @Test
    public void testRetrieveOneOrder() {
        // Given
        User user = new User("John","Smith");
        User savedUser = userRepository.save(user);
        Cart cart = new Cart(user);
        Order order = new Order(cart, OrderStatus.CREATED);
        Order savedOrder = orderRepository.save(order);
        long id = savedOrder.getId();
        // When
        Order orderDb = orderRepository.findById(id).get();
        // Then
        assertThat(orderDb).isNotNull();
        assertThat(orderDb.getCart()).isNotNull();
        assertThat(orderDb.getId()).isGreaterThan(0);

        orderRepository.deleteById(orderDb.getId());
        userRepository.deleteById(savedUser.getId());
    }

    @Test
    public void testUpdateOrder() {
        // Given
        User user = new User("John","Smith");
        User savedUser = userRepository.save(user);
        Cart cart = new Cart(user);
        Order order = new Order(cart, OrderStatus.CREATED);
        Order savedOrder = orderRepository.save(order);
        long id = savedOrder.getId();
        // When
        Order orderDb = orderRepository.findById(id).get();
        orderDb.setStatus(OrderStatus.SENT);
        // Then
        assertThat(orderDb).isNotNull();
        assertThat(orderDb.getCart()).isNotNull();
        assertThat(orderDb.getId()).isGreaterThan(0);
        assertEquals(OrderStatus.SENT,orderDb.getStatus());

        orderRepository.deleteById(orderDb.getId());
        userRepository.deleteById(savedUser.getId());
    }

    @Test
    public void testDeleteOrder_cartShouldBeDeleted() {
        // Given
        User user = new User("John","Smith");
        User savedUser = userRepository.save(user);
        Cart cart = new Cart(user);
        Order order = new Order(cart, OrderStatus.CREATED);
        Order savedOrder = orderRepository.save(order);
        long orderId = savedOrder.getId();
        long cartId = cart.getId();
        // When
        orderRepository.deleteById(orderId);
        userRepository.deleteById(savedUser.getId());
        // Then
        boolean orderExists = orderRepository.existsById(orderId);
        boolean cartExists = cartRepository.existsById(cartId);
        assertThat(orderExists).isEqualTo(false);
        assertThat(cartExists).isEqualTo(false);
    }

    @Test(expected = OrderNotFoundException.class)
    public void testFetchOrderThatDoesNotExists_throwsOrderNotFoundException() {
        // Given
        long fakeId = 1;
        // When Then
        orderRepository.findById(fakeId).orElseThrow(OrderNotFoundException::new);
    }
}
