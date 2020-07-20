package com.example.service.impl;

import com.example.dao.PaymentDao;
import com.example.domain.Payment;
import com.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        try {
            paymentDao.save(payment);
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public Payment getPaymentById(Integer id) {
        return paymentDao.findById(id).get();
    }
}
