package com.ecommerce.indo.repo;

import com.ecommerce.indo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("select p from Product p where (p.category.name=:category or :category='') and ((:minPrice IS NULL and :maxPrice IS NULL) or (p.discountedPrice between :minPrice and :maxPrice)) and (:minDiscount IS NULL or p.discountPercent >= :minDiscount) order by case when :sort='price_low' then p.discountedPrice end asc, case when :sort='price_high' then p.discountedPrice end desc ")
    List<Product> filterProduct(String category, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort);
}
