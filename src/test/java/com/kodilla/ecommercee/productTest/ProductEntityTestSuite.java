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
        productRepository.save(product);


        //Then
        int numberOfGroups = groupRepository.findAll().size();
        int numberOfProducts = productRepository.findAll().size();
        assertEquals(numberOfGroups,1);
        assertEquals(numberOfProducts,1);
        System.out.println(numberOfGroups);
        System.out.println(numberOfProducts);

        //cleanup
        productRepository.deleteAll(); // usuwa też grupę, a nie powinien
        System.out.println(productRepository.findAll().size());
        System.out.println(groupRepository.findAll().size());
    }

    @Test
    public void testAddProductToCart() {
    //czy spowoduje ładne rzeczy w odpowiednich tabelach?
        //w sumie to powinien się pojawić po prostu nowy rekord w zewnętrznej tabeli
    }


    @Test
    public void testRemoveProduct() {
    //czy spowoduje usunięcie grupy i cartów?
        //powinno tylko spowodować usunięcie z listy produków. Lista cartów i grup powinna zostać nie zmieniona
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
