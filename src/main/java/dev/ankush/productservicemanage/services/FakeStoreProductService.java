package dev.ankush.productservicemanage.services;

import dev.ankush.productservicemanage.dto.GenericProductDto;
import dev.ankush.productservicemanage.exceptions.NotFoundException;
import dev.ankush.productservicemanage.thirdpartyclients.fakestore.FakeStoreProductClient;
import dev.ankush.productservicemanage.thirdpartyclients.fakestore.dto.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//SERVICE class only handel business logic not for API call that why
//we created client class separately to handel the API Calls to interact with data
//and give that data in backend

//"@Primary" annotation is used to indicate that a particular bean should be given higher
// preference when multiple beans of the same type are present in the application context


@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private FakeStoreProductClient fakeStoreProductClient;

    @Autowired
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient) {
        this.fakeStoreProductClient = fakeStoreProductClient;
    }
    public GenericProductDto convertFakeStoreDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setImage(fakeStoreProductDto.getImage());

        return genericProductDto;
    }

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {

        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient
                .getProductById(id));

    }

    @Override
    public Optional<GenericProductDto> createProduct(GenericProductDto product) {

       GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient
                                                                            .createProduct(product));
        return Optional.of(genericProductDto);
    }
    @Override
    public List<GenericProductDto> getAllProducts() {

        List<FakeStoreProductDto> fakeStoreProductDtos =
                fakeStoreProductClient.getAllProducts();

        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos){

            GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto
                    (fakeStoreProductDto);


            genericProductDtos.add(genericProductDto);
        }
        return genericProductDtos;
    }
    @Override
    public Optional<GenericProductDto> deleteProduct(String id) {


        GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.deleteProduct(id));
        return Optional.of(genericProductDto);


    }
    @Override
    public GenericProductDto updateProduct(String id, GenericProductDto genericProductDto) {

        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.updateProduct(id, genericProductDto));

    }
}
