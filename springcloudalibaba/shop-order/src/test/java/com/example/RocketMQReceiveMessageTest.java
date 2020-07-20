package com.example;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class RocketMQReceiveMessageTest {
    public static void main(String[] args) throws Exception{
        //1 创建消费者，并为其指定组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("myconsumer-group");
        //2 未消费者设置NameServer地址
        consumer.setNamesrvAddr("192.168.18.128:9876");
        //3 指定消费者订阅的主题和标签(所有)
        consumer.subscribe("myTopic","*");
        //4 设置一个回调函数，并在函数中编写接收到消息之后的处理方法
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            //处理获取到的消息
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                //消费逻辑
                System.out.println("Message:"+list);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //5 启动消费者
        consumer.start();
    }
}
