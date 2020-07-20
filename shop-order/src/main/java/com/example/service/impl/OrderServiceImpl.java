package com.example.service.impl;


import com.example.dao.OrderDao;
import com.example.domain.Order;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public void createOrder(Order order) {
        orderDao.save(order);
    }
}
