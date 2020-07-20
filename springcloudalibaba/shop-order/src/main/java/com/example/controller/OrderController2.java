package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.domain.Order;
import com.example.domain.Product;
import com.example.service.ProductService;
import com.example.service.impl.OrderServiceImpl1;
import com.example.service.impl.OrderServiceImpl2;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class OrderController2 {
    @Autowired
    private OrderServiceImpl2 orderService;

    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        return orderService.createOrder(pid);
    }
}
