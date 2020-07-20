package com.example.service.fallback;

import com.example.domain.Product;
import com.example.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceFallback implements ProductService {
    @Override
    public Product findByPid(Integer pid) {
        Product p = new Product();
        p.setPid(-100);
        return p;
    }

    @Override
    public void reduceInventory(Integer pid, Integer number) {

    }
}
