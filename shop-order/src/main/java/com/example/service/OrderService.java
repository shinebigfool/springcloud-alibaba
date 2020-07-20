package com.example.service;

import com.example.domain.Order;
import org.springframework.stereotype.Service;


public interface OrderService {

    void createOrder(Order order);
}
