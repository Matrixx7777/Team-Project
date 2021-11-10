package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.status.OrderStatus;


import org.assertj.core.api.Assertions;
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
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreateNewOrder() {
        // Given
        Group group = new Group("Bakery products");
        Product bread = new Product("Bread","Description",2.99,group);
        Product roll = new Product("Roll","Description",0.59,group);
        Product muffin = new Product("Muffin","Description",4.99,group);
        group.addProduct(bread);
        group.addProduct(roll);
        group.addProduct(muffin);
        User user = new User("John","Smith");
        Cart cart = new Cart(user);
        user.addCart(cart);
        cart.addProduct(bread);
        cart.addProduct(roll);
        cart.addProduct(muffin);
        Order order = new Order(cart, OrderStatus.CREATED);
        cart.setOrder(order);
        // When
        Order savedOrder = orderRepository.save(order);
        // Then
        long id = savedOrder.getId();
        assertThat(id).isGreaterThan(0);
        assertThat(savedOrder.getCart()).isNotNull();
        assertThat(cart.getOrder()).isNotNull();
        assertEquals(3,group.getProductList().size());

        orderRepository.deleteById(id);
    }

    @Test
    public void testRetrieveAllOrders() {
        // Given
        Group group = new Group("Bakery products");
        Product bread = new Product("Bread","Description",2.99,group);
        Product roll = new Product("Roll","Description",0.59,group);
        Product muffin = new Product("Muffin","Description",4.99,group);
        group.addProduct(bread);
        group.addProduct(roll);
        group.addProduct(muffin);
        User user = new User("John","Smith");
        Cart cart1 = new Cart(user);
        Cart cart2 = new Cart(user);
        Cart cart3 = new Cart(user);
        user.addCart(cart1);
        user.addCart(cart2);
        user.addCart(cart3);
        cart1.addProduct(bread);
        cart1.addProduct(roll);
        cart1.addProduct(muffin);
        cart2.addProduct(roll);
        cart2.addProduct(muffin);
        cart3.addProduct(bread);
        cart3.addProduct(roll);
        Order order1 = new Order(cart1, OrderStatus.CREATED);
        Order order2 = new Order(cart2, OrderStatus.CREATED);
        Order order3 = new Order(cart3, OrderStatus.CREATED);
        cart1.setOrder(order1);
        cart2.setOrder(order2);
        cart3.setOrder(order3);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
        // When
        List<Order> savedOrders = (List<Order>) orderRepository.findAll();
        // Then
        assertEquals(3,savedOrders.size());
        assertThat(user.getCarts().contains(cart1)).isEqualTo(true);

        orderRepository.deleteById(order1.getId());
        orderRepository.deleteById(order2.getId());
        orderRepository.deleteById(order3.getId());
    }

    @Test
    public void testRetrieveOneOrder() {
        // Given
        Group group = new Group("Bakery products");
        Product bread = new Product("Bread","Description",2.99,group);
        Product roll = new Product("Roll","Description",0.59,group);
        Product muffin = new Product("Muffin","Description",4.99,group);
        group.addProduct(bread);
        group.addProduct(roll);
        group.addProduct(muffin);
        User user = new User("John","Smith");
        Cart cart = new Cart(user);
        user.addCart(cart);
        cart.addProduct(bread);
        cart.addProduct(roll);
        cart.addProduct(muffin);
        Order order = new Order(cart, OrderStatus.CREATED);
        cart.setOrder(order);
        Order savedOrder = orderRepository.save(order);
        long id = savedOrder.getId();
        // When
        Order orderDb = orderRepository.findById(id).get();
        // Then
        assertThat(orderDb).isNotNull();
        assertThat(orderDb.getCart()).isNotNull();
        assertThat(orderDb.getCart()).isNotNull();
        assertThat(cart.getOrder()).isNotNull();
        assertThat(orderDb.getId()).isGreaterThan(0);

        orderRepository.deleteById(orderDb.getId());
    }

    @Test
    public void testUpdateOrder() {
        // Given
        Group group = new Group("Bakery products");
        Product bread = new Product("Bread","Description",2.99,group);
        Product roll = new Product("Roll","Description",0.59,group);
        Product muffin = new Product("Muffin","Description",4.99,group);
        group.addProduct(bread);
        group.addProduct(roll);
        group.addProduct(muffin);
        User user = new User("John","Smith");
        Cart cart = new Cart(user);
        user.addCart(cart);
        cart.addProduct(bread);
        cart.addProduct(roll);
        cart.addProduct(muffin);
        Order order = new Order(cart, OrderStatus.CREATED);
        cart.setOrder(order);
        Order savedOrder = orderRepository.save(order);
        long id = savedOrder.getId();
        long cartId = cart.getId();
        Cart savedCart = cartRepository.findById(cartId).get();
        // When
        Order orderDb = orderRepository.findById(id).get();
        orderDb.setStatus(OrderStatus.SENT);
        // Then
        assertThat(orderDb).isNotNull();
        assertThat(orderDb.getCart()).isNotNull();
        assertThat(orderDb.getId()).isGreaterThan(0);
        assertThat(savedCart.getOrder()).isNotNull();
        assertEquals(OrderStatus.SENT,orderDb.getStatus());

        orderRepository.deleteById(orderDb.getId());
    }

    @Test
    public void testDeleteOrder_cartShouldBeDeleted() {
        // Given
        Group group = new Group("Bakery products");
        Product bread = new Product("Bread","Description",2.99,group);
        Product roll = new Product("Roll","Description",0.59,group);
        Product muffin = new Product("Muffin","Description",4.99,group);
        group.addProduct(bread);
        group.addProduct(roll);
        group.addProduct(muffin);
        User user = new User("John","Smith");
        Cart cart = new Cart(user);
        user.addCart(cart);
        cart.addProduct(bread);
        cart.addProduct(roll);
        cart.addProduct(muffin);
        Order order = new Order(cart, OrderStatus.CREATED);
        Order savedOrder = orderRepository.save(order);
        long orderId = savedOrder.getId();
        long cartId = cart.getId();
        long userId = user.getId();
        long productId = bread.getId();
        // When
        orderRepository.deleteById(orderId);
        // Then
        boolean orderExists = orderRepository.existsById(orderId);
        boolean cartExists = cartRepository.existsById(cartId);
        boolean userExists = userRepository.existsById(userId);
        boolean productExists = productRepository.existsById(productId);
        boolean productContainsCart = bread.getCarts().contains(cart);
        assertThat(orderExists).isEqualTo(false);
        assertThat(cartExists).isEqualTo(false);
        assertThat(userExists).isEqualTo(true);
        assertThat(productExists).isEqualTo(true);
        assertThat(productContainsCart).isEqualTo(false);
    }

    @Test(expected = OrderNotFoundException.class)
    public void testFetchOrderThatDoesNotExists_throwsOrderNotFoundException() {
        // Given
        long fakeId = 1;
        // When Then
        orderRepository.findById(fakeId).orElseThrow(() -> new OrderNotFoundException("Could not find order with given id"));
    }
}
