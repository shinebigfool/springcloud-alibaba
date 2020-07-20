package com.example.service.impl;


import com.example.dao.OrderDao;
import com.example.dao.TxLogDao;
import com.example.domain.Order;
import com.example.domain.TxLog;
import com.example.service.OrderService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl1{
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private TxLogDao txLogDao;
    public void createOrderBefore(Order order){
        String txId = UUID.randomUUID().toString();
        rocketMQTemplate.sendMessageInTransaction(
                "tx_producer_group",
                "order-topic",
                MessageBuilder.withPayload(order).setHeader("txId",txId).build(),
                order
        );
    }
    public void createOrder(String txId,Order order) {
        orderDao.save(order);
        TxLog txLog = new TxLog();
        txLog.setCreatedate(new Date());
        txLog.setTxId(txId);
        txLogDao.save(txLog);
    }
}
