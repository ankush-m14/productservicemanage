package dev.ankush.productservicemanage.services;

import dev.ankush.productservicemanage.dto.FakeStoreCategoryDto;
import dev.ankush.productservicemanage.dto.GenericCategoryDto;
import dev.ankush.productservicemanage.dto.GenericProductDto;
import dev.ankush.productservicemanage.thirdpartyclients.fakestore.FakeStoreCategoryClient;
import dev.ankush.productservicemanage.thirdpartyclients.fakestore.dto.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductCategoryService")
public class FakeStoreProductCategoryService implements CategoryService {

    private FakeStoreCategoryClient fakeStoreCategoryClient;

    @Autowired
    public FakeStoreProductCategoryService(FakeStoreCategoryClient fakeStoreCategoryClient) {
        this.fakeStoreCategoryClient = fakeStoreCategoryClient;
    }

    public List<GenericProductDto> getProductsByCategory(String category) {
        List<FakeStoreProductDto> fakeStoreProduct = fakeStoreCategoryClient.getProductsByCategory(category);
        int size = fakeStoreProduct.size();
        List<GenericProductDto> genericProductDtoList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            FakeStoreProductDto fakeStoreProductDto = fakeStoreProduct.get(i);
            GenericProductDto genericProductDto = convertToGenericProductDto(fakeStoreProductDto);
            genericProductDtoList.add(genericProductDto);
        }
        return genericProductDtoList;
    }

    @Override
    public List<String> getAllCategory() {
        return null;
    }

    public GenericProductDto convertToGenericProductDto (FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto newGenericProductDto = new GenericProductDto();
        newGenericProductDto.setId(fakeStoreProductDto.getId());
        newGenericProductDto.setTitle(fakeStoreProductDto.getTitle());
        newGenericProductDto.setDescription(fakeStoreProductDto.getDescription());
        newGenericProductDto.setCategory(fakeStoreProductDto.getCategory());
        newGenericProductDto.setImage(fakeStoreProductDto.getImage());
        newGenericProductDto.setPrice(fakeStoreProductDto.getPrice());
        return newGenericProductDto;
    }
    public GenericCategoryDto convertToGenericCategoryDto(FakeStoreCategoryDto fakeStoreCategoryDto) {
        GenericCategoryDto genericCategoryDto = new GenericCategoryDto();
        genericCategoryDto.setName(fakeStoreCategoryDto.getName());
        return genericCategoryDto;
    }
}
