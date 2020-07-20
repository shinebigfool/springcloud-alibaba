package com.example;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class MessageTypeTest {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Test
    public void testSyncSend(){
        rocketMQTemplate.syncSend("test-topic-1","这是一条同步消息");
    }
    @Test
    public void testAsynSend() throws Exception{
        rocketMQTemplate.asyncSend("test-topic-1:sayncsend", "这是一条异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable);
            }
        });
        System.out.println("=========================");
        Thread.sleep(1000);
    }
    @Test
    public void testOneway(){
        rocketMQTemplate.sendOneWay("test-topic-1:oneway","这是一条单向消息");
    }
    @Test
    public void testOnewayOrderly(){
        for(int i=0;i<10;i++){
            //第三个参数决定发送到哪个队列上
            rocketMQTemplate.sendOneWayOrderly("test-topic-1:oneway","这是一条单向消息","xx");
        }
    }
}
