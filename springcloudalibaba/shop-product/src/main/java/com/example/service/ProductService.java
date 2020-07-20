package com.example.service;

import com.example.domain.Product;

public interface ProductService {
    Product findById(Integer pid);

    void reduceInventory(Integer pid, Integer number);
}
