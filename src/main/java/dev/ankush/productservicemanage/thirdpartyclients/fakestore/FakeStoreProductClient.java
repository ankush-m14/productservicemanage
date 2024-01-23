package dev.ankush.productservicemanage.thirdpartyclients.fakestore;


import dev.ankush.productservicemanage.dto.GenericProductDto;
import dev.ankush.productservicemanage.exceptions.NotFoundException;
import dev.ankush.productservicemanage.thirdpartyclients.fakestore.dto.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

//This class is created for API caller interacting over the network
//to the third party system.
@Component
public class FakeStoreProductClient {

    @Value("${fakestore.api.baseurl}")
    private String fakeStoreApiBaseUrl;

    @Value("${fakestore.api.product}")
    private String fakeStoreProductPath;

    private final String productPath = "/products";

    private String productUrl = fakeStoreApiBaseUrl + productPath + "/{id}";
    private String productRequestUrl = fakeStoreApiBaseUrl + fakeStoreProductPath;

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.baseurl}") String fakeStoreApiBaseUrl,
                                  @Value("${fakestore.api.product}") String fakeStoreProductPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productUrl = fakeStoreApiBaseUrl + productPath + "/{id}";
        this.productRequestUrl =  fakeStoreApiBaseUrl + fakeStoreProductPath;
    }

    public FakeStoreProductDto getProductById(String id) throws NotFoundException {

        //RestTemplateBuilder is the tool provided by RestTemplate
        //to help us to creat the object of restTemplate because directly creating is difficult.

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(
                productUrl,
                FakeStoreProductDto.class,
                id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with id " + id + " not found");
        }


        return  fakeStoreProductDto;

    }

    public FakeStoreProductDto createProduct(GenericProductDto product) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response = restTemplate
                .postForEntity(productRequestUrl,
                        product,
                        FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto =  response.getBody();


        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.
                getForEntity(productRequestUrl,
                        FakeStoreProductDto[].class);

        //use Array here FakeStoreProductDto[] and not using ArrayList
        //Because of Type Erasure information is erased, and this is effectively
        // List (raw type) at runtime.
        // This will compile, but may result in a runtime ClassCastException
        // because the JVM doesn't have type information about the generic types.
        // It's as if you are casting List to List<Object>.
        //By using springBoot farm work "ParameterizedTypeReference<List<MyObject>>() {}"
        //new ParameterizedTypeReference<List<String>>() {} helps your computer remember
        // that you want a list of words. It's like a note that says,
        // "Remember, this is a list of strings!"

        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();


        return Arrays.asList(fakeStoreProductDtos);
    }


    public FakeStoreProductDto deleteProduct(String id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        //we have option "restTemplate.delete()" but this method is void
        //and not returning we want that why we use "restTemplate.exchange()" method
        //by using this we perform delete method.

        ResponseEntity<FakeStoreProductDto> response = restTemplate
                .exchange(productUrl,
                        HttpMethod.DELETE,null,
                        FakeStoreProductDto.class, id);


        FakeStoreProductDto fakeStoreProductDto =  response.getBody();

        return fakeStoreProductDto;
    }


    public FakeStoreProductDto updateProduct(String id, GenericProductDto genericProductDto) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange
                (productUrl,
                        HttpMethod.PUT,null,
                        FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto =  response.getBody();

        return fakeStoreProductDto;
    }
}