package com.ecommerce.indo.repo;

import com.ecommerce.indo.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepo extends JpaRepository<Rating,Long> {

    @Query("select r from Rating r where r.product.id=:productId")
    List<Rating> findAllProductsRating(Long productId);
}
