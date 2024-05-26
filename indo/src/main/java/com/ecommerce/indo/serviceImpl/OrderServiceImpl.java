package com.ecommerce.indo.serviceImpl;

import com.ecommerce.indo.model.Address;
import com.ecommerce.indo.model.AppUser;
import com.ecommerce.indo.model.OrderItem;
import com.ecommerce.indo.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public OrderItem createOrder(AppUser user, Address shippingAddress) {
        return null;
    }

    @Override
    public OrderItem fetchOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<OrderItem> usersOrderHistory(Long userId) {
        return List.of();
    }

    @Override
    public OrderItem placedOrder(Long orderId) {
        return null;
    }

    @Override
    public OrderItem confirmedOrder(Long orderId) {
        return null;
    }

    @Override
    public OrderItem shippedOrder(Long orderId) {
        return null;
    }

    @Override
    public OrderItem deliveredOrder(Long orderId) {
        return null;
    }

    @Override
    public OrderItem cancelledOrder(Long orderId) {
        return null;
    }

    @Override
    public List<OrderItem> fetchAllOrder() {
        return List.of();
    }

    @Override
    public void deleteOrder(Long orderId) {

    }
}
