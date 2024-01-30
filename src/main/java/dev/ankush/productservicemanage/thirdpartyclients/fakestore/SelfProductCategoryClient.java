package dev.ankush.productservicemanage.thirdpartyclients.fakestore;

import dev.ankush.productservicemanage.models.Category;
import dev.ankush.productservicemanage.models.Product;
import dev.ankush.productservicemanage.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SelfProductCategoryClient {

    CategoryRepository categoryRepository;

    @Autowired
    public SelfProductCategoryClient(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getInCategory (String name){
        List<Category> categories = categoryRepository.findCategoryByName(name);
        List<Product> productList = new ArrayList<>();

        for(Category category : categories){
            for(Product product : category.getProduct()){
                productList.add(product);
            }
        }
        return productList;
    }
    public List<String> getAllCategories() {
        List<String> categories = categoryRepository.getAllCategories();
        return categories;
    }

}
