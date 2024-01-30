package dev.ankush.productservicemanage.services;

import dev.ankush.productservicemanage.dto.GenericProductDto;
import dev.ankush.productservicemanage.models.Product;
import dev.ankush.productservicemanage.thirdpartyclients.fakestore.SelfProductCategoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("SelfProductCategoryService")
public class SelfProductCategoryService implements CategoryService{

    public SelfProductCategoryClient selfProductCategoryClient;

    @Autowired
    public SelfProductCategoryService(SelfProductCategoryClient selfProductCategoryClient) {
        this.selfProductCategoryClient = selfProductCategoryClient;
    }

    @Override
    public List<GenericProductDto> getProductsByCategory(String category) {

        List<Product> productList = selfProductCategoryClient.getInCategory(category);

        int size = productList.size();

        List<GenericProductDto> genericProductDtoList = new ArrayList<>();

        for (int i=0; i<size; i++){
            Product product = productList.get(i);

            GenericProductDto genericProductDto = convertToGenericProductDto(product);

            genericProductDtoList.add(genericProductDto);
        }

        return genericProductDtoList;
    }

    @Override
    public List<String> getAllCategory() {

        return selfProductCategoryClient.getAllCategories();
    }
    public GenericProductDto convertToGenericProductDto(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setId(String.valueOf(product.getUuid()));
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setPrice(product.getPrice());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setCategory(product.getCategory().getName());

        return genericProductDto;
    }
}
