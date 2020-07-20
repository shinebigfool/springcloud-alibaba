package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.domain.Product;
import com.example.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;
    @RequestMapping("/product/{pid}")
    public Product product(@PathVariable("pid") Integer pid){
        log.info("接下来要进行{}号商品信息的查询",pid);
        Product p = productService.findById(pid);
        log.info("商品信息查询成功，内容为{}", JSON.toJSONString(p));
        return p;
    }
    //扣库存
    @RequestMapping("/product/reduceInventory")
    public void reduceInventory(@RequestParam("pid") Integer pid,
                                @RequestParam("number") Integer number){
        productService.reduceInventory(pid,number);

    }
}
