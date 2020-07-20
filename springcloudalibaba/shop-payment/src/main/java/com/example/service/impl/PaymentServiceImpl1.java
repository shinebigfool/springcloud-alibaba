package com.example.service.impl;

import com.example.dao.PaymentDao;
import com.example.dao.PaymentDao1;
import com.example.domain.Payment;
import com.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl1 {
    @Autowired
    private PaymentDao1 paymentDao1;

    public int create(Payment payment) {
        try {

            return paymentDao1.create(payment);
        }catch (Exception e){
            System.out.println("出现异常");
            return -1;
        }
    }

    public Payment getPaymentById(Integer id) {
        return paymentDao1.getPaymentById(id);
    }
}
