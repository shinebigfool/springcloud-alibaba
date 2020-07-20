package com.example.service.impl;


import com.alibaba.fastjson.JSON;
import com.example.dao.OrderDao;
import com.example.domain.Order;
import com.example.domain.Product;
import com.example.service.OrderService;
import com.example.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl2{
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private ProductService productService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    public Order createOrder(Integer pid) {
        //1 查询商品信息
        log.info("接受到{}号商品的下单请求，接下来调用商品微服务查询此商品信息",pid);
        List<ServiceInstance> instances =  discoveryClient.getInstances("service-product");
        //使用Fegin
        Product p  = productService.findByPid(pid);
        if(p.getPid()==-100){
            Order o = new Order();
            o.setOid(-100L);
            o.setPname("下单失败");
            return o;
        }
        log.info("查询到{}号商品的信息，内容是{}",pid, JSON.toJSONString(p));
        //2 下单
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(p.getPid());
        order.setPname(p.getPname());
        order.setPprice(p.getPprice());
        order.setNumber(1);
        orderDao.save(order);
        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
        //3 扣库存
        productService.reduceInventory(pid,order.getNumber());
        //4 向mq投递信息
        rocketMQTemplate.convertAndSend("order-topic",order);
        return order;
    }
}
