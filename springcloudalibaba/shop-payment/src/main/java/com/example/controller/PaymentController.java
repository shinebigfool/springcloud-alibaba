package com.example.controller;

import com.example.domain.CommonResult;
import com.example.domain.Payment;
import com.example.service.PaymentService;
import com.example.service.impl.PaymentServiceImpl1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentServiceImpl1 paymentServiceImpl1;
    @RequestMapping(value = "/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int result = paymentServiceImpl1.create(payment);
        log.info("插入结果："+result);
        if(result>0){
            return new CommonResult<>(200,"插入数据库成功",result);
        }else {
            return new CommonResult<>(444, "插入数据库失败", null);
        }
    }
    @RequestMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Integer id){
        try {
//            Payment payment = paymentService.getPaymentById(id);
            Payment payment = paymentServiceImpl1.getPaymentById(id);
            if (payment != null) {
                return new CommonResult<>(200, "查询成功", payment);
            } else {
                return new CommonResult<>(444, "没有对应记录，查询ID：" + id, null);
            }
        }
        catch (Exception e){
            return new CommonResult<>(444, "异常，没有对应记录，查询ID：" + id, null);
        }
    }
}
