package dev.ankush.productservicemanage.repositories;

import dev.ankush.productservicemanage.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query(value = "select * from Category where Category.name = :name", nativeQuery = true)
    public List<Category> findCategoryByName(String name);

    @Query(value = "select DISTINCT Category.name from Category", nativeQuery = true)
    public List<String> getAllCategories();

}
