package controllers;

import dev.ankush.productservicemanage.controller.ProductController;
import dev.ankush.productservicemanage.dto.GenericProductDto;
import dev.ankush.productservicemanage.exceptions.NotFoundException;
import dev.ankush.productservicemanage.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @MockBean
    private ProductService productServiceTest;


    @Test
    public void testGetProductById() throws NotFoundException {
       when(productServiceTest.getProductById(any(String.class))).
               thenReturn(new GenericProductDto());

       GenericProductDto response = productController.getProductById("1");

        Assertions.assertNotNull(response);
    }
}
