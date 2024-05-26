package com.ecommerce.indo.service;

import com.ecommerce.indo.model.Address;
import com.ecommerce.indo.model.AppUser;
import com.ecommerce.indo.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(AppUser user, Address shippingAddress);

    Order fetchOrderById(Long orderId);

    List<Order> usersOrderHistory(Long userId);

    Order placedOrder(Long orderId);

    Order confirmedOrder(Long orderId);

    Order shippedOrder(Long orderId);

    Order deliveredOrder(Long orderId);

    Order cancelledOrder(Long orderId);

    List<Order> fetchAllOrder();

    void deleteOrder(Long orderId);
}
