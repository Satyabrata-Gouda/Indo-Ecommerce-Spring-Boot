package com.ecommerce.indo.serviceImpl;

import com.ecommerce.indo.exception.AuthException;
import com.ecommerce.indo.model.*;
import com.ecommerce.indo.repo.CartRepo;
import com.ecommerce.indo.service.CartItemService;
import com.ecommerce.indo.service.CartService;
import com.ecommerce.indo.service.ProductService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;

    @Override
    public Cart createCart(AppUser user) {
        try {
            Cart cart = new Cart();
            cart.setUser(user);
            return cartRepo.save(cart);
        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }

    @Override
    public String addCartItem(Long userId, AddItemReq req) {
        try {
            Cart cart = cartRepo.findByUserId(userId);
            Product product = productService.fetchProdById(req.getProductId());
            CartItem isPresent = cartItemService.isCartItemExit(cart,product, req.getSize(), userId);

            if(ObjectUtils.isEmpty(isPresent)){
                CartItem item = new CartItem();
                item.setProduct(product);
                item.setCart(cart);
                item.setQuantity(req.getQuantity());
                item.setUserId(userId);

                Double price = req.getQuantity()*product.getDiscountedPrice();

                item.setPrice(price);
                item.setSize(req.getSize());
                CartItem cartItem = cartItemService.createCartItem(item);

                cart.getCartItems().add(cartItem);
            }

            return "Item added to cart.";
        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }

    @Override
    public Cart fetchUserCart(Long userId) {
        try {

            Cart cart = cartRepo.findByUserId(userId);

            Double totalPrice = 0.0;
            Double totalDiscountedPrice=0.0;
            Integer totalItem =0;

            for(CartItem item:cart.getCartItems()){
                totalPrice+=item.getPrice();
                totalDiscountedPrice+=item.getDiscountedPrice();
                totalItem+=item.getQuantity();
            }
            cart.setTotalDiscountedPrice(totalDiscountedPrice);
            cart.setTotalItem(totalItem);
            cart.setTotalPrice(totalPrice);
            cart.setDiscount(totalPrice-totalDiscountedPrice);


            return cartRepo.save(cart);
        } catch (Exception e) {
            throw new AuthException(e.getMessage());
        }
    }
}
