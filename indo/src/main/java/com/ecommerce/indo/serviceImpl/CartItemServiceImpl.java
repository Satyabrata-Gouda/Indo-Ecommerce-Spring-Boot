package com.ecommerce.indo.serviceImpl;

import com.ecommerce.indo.exception.AuthException;
import com.ecommerce.indo.model.AppUser;
import com.ecommerce.indo.model.Cart;
import com.ecommerce.indo.model.CartItem;
import com.ecommerce.indo.model.Product;
import com.ecommerce.indo.repo.CartItemRepo;
import com.ecommerce.indo.repo.CartRepo;
import com.ecommerce.indo.service.CartItemService;
import com.ecommerce.indo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepo cartRepo;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        try {
            cartItem.setQuantity(1);
            cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
            cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());
            return cartItemRepo.save(cartItem);
        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) {
        try {
            CartItem item = fetchCartItemById(id);
            AppUser user = userService.fetchUserById(item.getUserId());

            if (user.getId().equals(userId)) {
                item.setQuantity(cartItem.getQuantity());
                item.setPrice(item.getQuantity() * item.getProduct().getPrice());
                item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());

            }
            return cartItemRepo.save(item);

        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }

    @Override
    public CartItem isCartItemExit(Cart cart, Product product, String size, Long userId) {
        try {
            return cartItemRepo.isCartItemExit(cart, product, size, userId);

        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }

    @Override
    public void removeCarItem(Long userId, Long cartItemId) {
        try {
            CartItem item = fetchCartItemById(cartItemId);

            AppUser user = userService.fetchUserById(item.getUserId());
            AppUser reqUser = userService.fetchUserById(userId);
            if(user.getId().equals(reqUser.getId())){
                cartItemRepo.deleteById(cartItemId);
            }else {
                throw new AuthException("Invalid user found to perform action. ");
            }

        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }

    @Override
    public CartItem fetchCartItemById(Long cartItemId) {
        try {
            return cartItemRepo.findById(cartItemId).orElseThrow(()-> new AuthException("Cart item not found."));
        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }
}
