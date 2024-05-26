package com.ecommerce.indo.repo;

import com.ecommerce.indo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepo extends JpaRepository<Cart,Long> {

    @Query("select c from Cart c where c.user.id=:userId")
    Cart findByUserId(Long userId);
}
