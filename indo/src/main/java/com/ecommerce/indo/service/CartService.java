package com.ecommerce.indo.service;

import com.ecommerce.indo.model.AddItemReq;
import com.ecommerce.indo.model.AppUser;
import com.ecommerce.indo.model.Cart;

public interface CartService {

    Cart createCart(AppUser user);

    String addCartItem(Long userId, AddItemReq req);

    Cart fetchUserCart(Long userId);
}
