package dev.ankush.productservicemanage.thirdpartyclients.fakestore;

import dev.ankush.productservicemanage.thirdpartyclients.fakestore.dto.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreCategoryClient {
    private RestTemplateBuilder restTemplateBuilder;
    String getByCategoryUrl;
    String getAllCategoriesUrl;

    @Autowired
    public FakeStoreCategoryClient(RestTemplateBuilder restTemplateBuilder,
                                   @Value("https://fakestoreapi.com")String fakeStoreBaseUrl,
                                   @Value("/products")String productUrl,
                                   @Value("/category") String getInCategoryUrl,
                                   @Value("/categories")String getAllCategoriesUrl) {

        this.restTemplateBuilder = restTemplateBuilder;
        this.getByCategoryUrl = fakeStoreBaseUrl + productUrl + getInCategoryUrl + "{/category}";
        this.getAllCategoriesUrl = fakeStoreBaseUrl + productUrl + getAllCategoriesUrl;
    }

    public List<FakeStoreProductDto> getProductsByCategory(String category) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List<FakeStoreProductDto>> response = restTemplate.exchange(getByCategoryUrl,HttpMethod.GET, null, new ParameterizedTypeReference<List<FakeStoreProductDto>>() {
        }, category);
        List<FakeStoreProductDto> fakeStoreProductDtoList = response.getBody();
        return fakeStoreProductDtoList;
    }
    public List<String> getAllCategories() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List<String>> response = restTemplate.exchange(getAllCategoriesUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
        });
        List<String> fakeStoreCategoryList = response.getBody();
        return fakeStoreCategoryList;
    }
}
