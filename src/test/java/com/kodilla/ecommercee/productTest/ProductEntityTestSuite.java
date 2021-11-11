package com.kodilla.ecommercee.productTest;


import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEntityTestSuite {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testProductSave() {

        //Given
        Group group = new Group("Test group");
        Product product = new Product("Test product","Test product desc", 24.99, group);
        //When
        //groupRepository.save(group);
        productRepository.save(product);


        //Then
        int numberOfGroups = groupRepository.findAll().size();
        int numberOfProducts = productRepository.findAll().size();
        assertEquals(numberOfGroups,1);
        assertEquals(numberOfProducts,1);
        System.out.println(numberOfGroups);
        System.out.println();

        //cleanup

    }


}
