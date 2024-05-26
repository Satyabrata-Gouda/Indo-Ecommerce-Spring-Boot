package com.ecommerce.indo.repo;

import com.ecommerce.indo.model.Cart;
import com.ecommerce.indo.model.CartItem;
import com.ecommerce.indo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepo extends JpaRepository<CartItem,Long> {

    @Query("select ci from CartItem ci where ci.cart=:cart and ci.product=:product and ci.size=:size and ci.userId=:userId")
    CartItem isCartItemExit(Cart cart, Product product,String size, Long userId);
}
