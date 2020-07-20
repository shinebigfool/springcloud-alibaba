package com.example.service.impl;


import com.example.dao.OrderDao;
import com.example.dao.TxLogDao;
import com.example.domain.Order;
import com.example.domain.TxLog;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RocketMQTransactionListener(txProducerGroup = "tx_producer_group")
public class OrderServiceImpl1Listener implements RocketMQLocalTransactionListener {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private OrderServiceImpl1 orderServiceImpl1;
    @Autowired
    private TxLogDao txLogDao;
    //执行本地事务
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        String txId = (String) message.getHeaders().get("txId");
        try {
            Order order = (Order)o;
            orderServiceImpl1.createOrder(txId,order);
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            return RocketMQLocalTransactionState.ROLLBACK;
        }


    }
    //消息回查
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String txId = (String) message.getHeaders().get("txId");
        TxLog txLog = txLogDao.findById(txId).get();
        if(txLog!=null){
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
