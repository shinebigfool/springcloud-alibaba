package com.example.service;

import com.example.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "shop-user",topic = "order-topic")
public class SmsService implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.info("接受到了一个订单信息{}，接下来可以发送短信通知了",order);
    }
}
