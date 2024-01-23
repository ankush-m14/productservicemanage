package dev.ankush.productservicemanage.services;

import dev.ankush.productservicemanage.dto.GenericProductDto;
import dev.ankush.productservicemanage.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public GenericProductDto getProductById(String id) throws NotFoundException;

    public Optional<GenericProductDto> createProduct(GenericProductDto genericProductDto);

    public List<GenericProductDto> getAllProducts();

    public Optional<GenericProductDto> deleteProduct(String id);
    public GenericProductDto updateProduct(String id, GenericProductDto genericProductDto);
}
