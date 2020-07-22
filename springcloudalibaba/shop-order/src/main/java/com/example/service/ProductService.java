package com.example.service;

import com.example.domain.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-product"//,
        //fallbackFactory = ProductServiceFallbackFactory.class
)
public interface ProductService {
    @RequestMapping("/product/{pid}")
    Product findByPid(@PathVariable Integer pid);
    //扣库存
    @RequestMapping("/product/reduceInventory")
    String reduceInventory(@RequestParam("pid") Integer pid,
                         @RequestParam("number") Integer number);
}
