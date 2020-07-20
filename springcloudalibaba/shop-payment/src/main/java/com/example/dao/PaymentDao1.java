package com.example.dao;

import com.example.domain.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao1 {
    int create(Payment payment);
    Payment getPaymentById(@Param("id") Integer id);
}
