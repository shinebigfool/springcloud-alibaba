package com.example.service.fallback;

import com.example.domain.Product;
import com.example.service.ProductService;
import org.springframework.stereotype.Service;
//容错类，需要实现feign所在接口，并实现接口中所有方法
//一旦远程调用出错，即进入容错类的同名方法
@Service
public class ProductServiceFallback implements ProductService {
    @Override
    public Product findByPid(Integer pid) {
        //容错逻辑
        Product p = new Product();
        p.setPid(-100);
        return p;
    }

    @Override
    public String reduceInventory(Integer pid, Integer number) {
        return "error";
    }
}
