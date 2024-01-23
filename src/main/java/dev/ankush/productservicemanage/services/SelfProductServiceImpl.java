package dev.ankush.productservicemanage.services;

import dev.ankush.productservicemanage.dto.GenericProductDto;
import dev.ankush.productservicemanage.exceptions.NotFoundException;
import dev.ankush.productservicemanage.models.Category;
import dev.ankush.productservicemanage.models.Product;
import dev.ankush.productservicemanage.thirdpartyclients.fakestore.SelfStoreProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{

   private SelfStoreProductClient selfStoreProductClient;

   @Autowired
   public SelfProductServiceImpl(SelfStoreProductClient selfStoreProductClient){
       this.selfStoreProductClient = selfStoreProductClient;
   }

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException{
        UUID uuid = UUID.fromString(id);

        Optional<Product> product = selfStoreProductClient.getProductById(uuid);

        return product.map(this::convertProductToGenericProductDto)
                      .orElseThrow(() -> new NotFoundException("The product with " + id + " is not found"));

    }

    @Override
    public Optional <GenericProductDto> createProduct(GenericProductDto genericProductDto) {
        Product product = convertGenericProductDtoToProduct(genericProductDto);
        selfStoreProductClient.creatProduct(product);
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
       List<Product> products = selfStoreProductClient.getAllProducts();

       List<GenericProductDto> genericProductDtoList = new ArrayList<>();

       for(Product product : products){
           genericProductDtoList.add(convertProductToGenericProductDto(product));
       }
        return genericProductDtoList;
    }

    @Override
    public Optional<GenericProductDto> deleteProduct(String id) {

       UUID uuid = UUID.fromString(id);
       selfStoreProductClient.deleteProduct(uuid);

        return null;
    }

    @Override
    public GenericProductDto updateProduct(String id, GenericProductDto genericProductDto) {
       Product product = convertGenericProductDtoToProduct(genericProductDto);
       UUID uuid = UUID.fromString(id);

       Product product1 = selfStoreProductClient.updateProduct(uuid, product);
        return convertProductToGenericProductDto(product1);
    }
    public Product convertGenericProductDtoToProduct(GenericProductDto genericProductDto){
         Product product = new Product();

         product.setTitle(genericProductDto.getTitle());
         product.setDescription(genericProductDto.getDescription());
         product.setPrice(genericProductDto.getPrice());
         product.setImage(genericProductDto.getImage());

         String category = genericProductDto.getCategory();
        Category category1 = new Category();
        category1.setName(category);

        product.setCategory(category1);

         return product;
    }
    public GenericProductDto convertProductToGenericProductDto(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setId(product.getUuid().toString());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setPrice(product.getPrice());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setImage(product.getImage());

        return genericProductDto;
    }
}
