package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.domain.Order;
import com.example.domain.Product;
import com.example.service.OrderService;
import com.example.service.ProductService;
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

//@RestController
@Slf4j
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private ProductService productService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接受到{}号商品的下单请求，接下来调用商品微服务查询此商品信息",pid);
        List<ServiceInstance> instances =  discoveryClient.getInstances("service-product");
        //随机选择服务 随机数
        /*
        int index = new Random().nextInt(instances.size());
        ServiceInstance serviceInstance = instances.get(index);
        ServiceInstance serviceInstance = instances.get(0);
        Product p = restTemplate.getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/product/"+pid,Product.class);
        */

        //基于Ribbon负载均衡

//        Product p = restTemplate.getForObject("http://service-product/product/"+pid,Product.class);

        //使用Fegin
        Product p  = productService.findByPid(pid);
//        Product p = productService.findByPid(pid);
        if(p.getPid()==-100){
            Order o = new Order();
            o.setOid(-100L);
            o.setPname("下单失败");
            return o;
        }
        log.info("查询到{}号商品的信息，内容是{}",pid, JSON.toJSONString(p));
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(p.getPid());
        order.setPname(p.getPname());
        order.setPprice(p.getPprice());
        order.setNumber(1);
        orderService.createOrder(order);
        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));
        //参数1指定topic 参数2指定消息
        rocketMQTemplate.convertAndSend("order-topic",order);
        return order;
    }
}
