package com.example.service;

import com.example.domain.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
 int create(Payment payment);
 Payment getPaymentById(@Param("id") Integer id);
 }
