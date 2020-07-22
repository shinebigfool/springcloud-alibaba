package com.example.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestService {
    //blockHandler  sentinel异常
    //fallback
    @SentinelResource(value = "message",blockHandler = "blockHandler",fallback = "fallBack")
    public String message(){
        return "message";
    }
    public String blockHandler(BlockException blockException){
        log.error("触发了blockException内容{}",blockException);
        return "blockException";
    }
    public String fallBack(Throwable e){
        log.error("触发了fallback内容{}",e);
        return "fallback";
    }
}
