package dev.ankush.productservicemanage.services;

import dev.ankush.productservicemanage.dto.GenericProductDto;

import java.util.List;

public interface CategoryService {
    public List<GenericProductDto> getProductsByCategory(String category);
    public List<String> getAllCategory();
}
