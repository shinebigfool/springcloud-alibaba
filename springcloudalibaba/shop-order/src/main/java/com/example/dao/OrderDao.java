package com.example.dao;

import com.example.domain.Order;
import com.example.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Integer> {
}
