package dev.ankush.productservicemanage.controller;

import dev.ankush.productservicemanage.dto.GenericProductDto;
import dev.ankush.productservicemanage.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{category}")
    public List<GenericProductDto> getInCategory(@PathVariable("category") String category){
        return categoryService.getProductsByCategory(category);
    }

    @GetMapping
    public List<String> getAllCategories(){
        return categoryService.getAllCategory();
    }

}
