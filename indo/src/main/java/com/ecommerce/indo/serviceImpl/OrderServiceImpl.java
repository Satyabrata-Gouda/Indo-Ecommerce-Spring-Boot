package com.ecommerce.indo.serviceImpl;

import com.ecommerce.indo.model.Address;
import com.ecommerce.indo.model.AppUser;
import com.ecommerce.indo.model.Order;
import com.ecommerce.indo.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(AppUser user, Address shippingAddress) {
        return null;
    }

    @Override
    public Order fetchOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return List.of();
    }

    @Override
    public Order placedOrder(Long orderId) {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) {
        return null;
    }

    @Override
    public Order cancelledOrder(Long orderId) {
        return null;
    }

    @Override
    public List<Order> fetchAllOrder() {
        return List.of();
    }

    @Override
    public void deleteOrder(Long orderId) {

    }
}
