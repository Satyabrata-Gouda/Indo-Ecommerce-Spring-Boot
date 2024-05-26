package com.ecommerce.indo.repo;

import com.ecommerce.indo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review,Long> {

    @Query("select r from Review r where r.product.id=:productId")
    List<Review> findAllProductsReview(Long productId);
}
