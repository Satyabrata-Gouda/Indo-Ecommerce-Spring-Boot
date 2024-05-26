package com.ecommerce.indo.repo;

import com.ecommerce.indo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepo extends JpaRepository<Category,Long> {

    Category findByName(String name);

    @Query("select c from Category c where c.name=:name and c.parentCategory.name=:parentCategoryName")
    Category findByNameAndParent(String name, String parentCategoryName);
}
