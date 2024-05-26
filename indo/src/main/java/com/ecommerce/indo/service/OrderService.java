package com.ecommerce.indo.service;

import com.ecommerce.indo.model.Address;
import com.ecommerce.indo.model.AppUser;
import com.ecommerce.indo.model.OrderItem;

import java.util.List;

public interface OrderService {

    OrderItem createOrder(AppUser user, Address shippingAddress);

    OrderItem fetchOrderById(Long orderId);

    List<OrderItem> usersOrderHistory(Long userId);

    OrderItem placedOrder(Long orderId);

    OrderItem confirmedOrder(Long orderId);

    OrderItem shippedOrder(Long orderId);

    OrderItem deliveredOrder(Long orderId);

    OrderItem cancelledOrder(Long orderId);

    List<OrderItem> fetchAllOrder();

    void deleteOrder(Long orderId);
}
