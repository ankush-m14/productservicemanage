package dev.ankush.productservicemanage.thirdpartyclients.fakestore;

import dev.ankush.productservicemanage.models.Product;
import dev.ankush.productservicemanage.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SelfStoreProductClient {

    private ProductRepository productRepository;

@Autowired
    public SelfStoreProductClient(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(UUID uuid) {
       Optional<Product> product = productRepository.findById(uuid);
       return product;
    }

    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products;
    }

    public void creatProduct(Product product){
       productRepository.save(product);
       return;
    }
    public Product updateProduct(UUID uuid, Product product){
       Optional<Product>product1 = productRepository.findById(uuid);
       Product product2 = product1.get();
       product2.setImage(product.getImage());
       product2.setPrice(product.getPrice());
       product2.setDescription(product.getDescription());

       productRepository.save(product2);

       return product2;
    }

    public void deleteProduct(UUID uuid){
      productRepository.deleteById(uuid);
    }

}
