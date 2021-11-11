package com.kodilla.ecommercee;


import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductEntityTestSuite {

    private ProductRepository repository;
    private GroupRepository
    private Group group = new Group("Test group");
    private Product product = new Product("Test product","Test product desc", 24.99, group);

    @Test
    void testProductSave() {

        //Given

        //group ma byc given
        //product ma byc given

        //When
        repository.save(group);
        //productSave
        //groupSave

        //Then

        //wziacId ze stworzonego obiektu, sprawdzic czy jest w bazie poprzez id
        //assert czy groups liczy 1
        //assert czy products liczy 1

        //cleanup

    }


}
