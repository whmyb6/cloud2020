package com.mywhm.springcloud.service;

import com.mywhm.springcloud.entities.Payment;

public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById( Long id);
}
