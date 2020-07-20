package com.example.controller;

import com.example.service.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//java -Dserver.port=8180 -Dcsp.sentinel.dashboard.server=localhost:8180 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.7.0.jar
@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @RequestMapping("/order/message")
    public String test(){
        return "message";
    }
    @RequestMapping("/order/message1")
    public String test1(){
        testService.message();
        return "message1";
    }
}
