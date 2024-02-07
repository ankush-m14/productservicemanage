package dev.ankush.productservicemanage.controller;


import dev.ankush.productservicemanage.dto.GenericProductDto;
import dev.ankush.productservicemanage.exceptions.NotFoundException;
import dev.ankush.productservicemanage.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//  @RestController is used when you want your Spring MVC controller to handle RESTful
// requests and return data directly, without rendering HTML views. It's commonly
// used in building APIs for web and mobile applications.
//It combines @Controller and @ResponseBody annotations. It is typically used in a Spring MVC
// application to create RESTful web services.
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    //If we have  multiple services in our project want to run a specific service
    //In a controller we use "@Qualifier" with service file name like(" @Qualifier("fakeStoreProductService") ")
    //But If we don't want to use Qualifier we have another way is "@Primary"
    //Just service file @Primary then the springboot default use tha service

    @Autowired
    public ProductController( ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") String id) throws NotFoundException {

        return productService.getProductById(id);
    }

    // "/products" in mapping is repeating so that we use
    //@RequestMapping to save that common name like "/products" on the top of class
    // @RequestMapping helps you map specific URLs to the methods in your code that handle those requests,
    // making it easier to organize and manage your web application.

    @GetMapping()
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    //    The @PathVariable annotation in the method signature tells Spring to extract
    //    the value from the URI and pass it as a parameter to the method. So, when a
    //    user goes to a URL like "/product/123", Spring will automatically set productId
    //    to 123 when calling the showProductDetails method.

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") String id,
                                               @RequestBody GenericProductDto genericProductDto){
        return productService.updateProduct(id, genericProductDto);
    }


    //@RequestBody is an annotation used to indicate that a method parameter should
    // be bound to the body of the HTTP request. It is typically used in conjunction
    // with the @PostMapping annotation to handle POST requests where the data is
    // sent in the request body.
    @PostMapping
    public Optional<GenericProductDto> creatProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);
    }

    @DeleteMapping("{id}")
    public Optional<GenericProductDto> deleteProduct(@PathVariable("id") String id){
        return productService.deleteProduct(id);
    }


    //This is specific to this controller
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDto> handelNotFoundException(NotFoundException notFoundException){
//          return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND,
//                                          notFoundException.getMessage()),
//                                          HttpStatus.NOT_FOUND);
//    }

}
