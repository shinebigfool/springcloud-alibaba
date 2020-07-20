package com.example.service.impl;


import com.example.dao.ProductDao;
import com.example.domain.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public Product findById(Integer pid) {
        return productDao.findById(pid).get();
    }

    @Override
    public void reduceInventory(Integer pid, Integer number) {
        Product product = productDao.findById(pid).get();
        product.setStock(product.getStock()-number);
        productDao.save(product);
    }
}
