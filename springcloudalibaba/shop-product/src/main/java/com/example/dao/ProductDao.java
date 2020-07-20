package com.example.dao;

import com.example.domain.Product;
import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer> {
}
