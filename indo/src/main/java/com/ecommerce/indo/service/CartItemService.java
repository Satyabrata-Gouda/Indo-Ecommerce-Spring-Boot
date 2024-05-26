package com.ecommerce.indo.service;

import com.ecommerce.indo.model.Cart;
import com.ecommerce.indo.model.CartItem;
import com.ecommerce.indo.model.Product;

public interface CartItemService {

    CartItem createCartItem(CartItem cartItem);

    CartItem updateCartItem(Long userId,Long id,CartItem cartItem);

    CartItem isCartItemExit(Cart cart, Product product, String size,Long userId);

    void removeCarItem(Long userId,Long cartItemId);

    CartItem fetchCartItemById(Long cartItemId);


}
