package com.mywhm.springcloud.service;

public interface PaymentService {
    public String paymentInfoOK(Integer id);
    public String paymentInfo_TimeOut(Integer id);
    public String payment_CircuitBreak(Integer id);

}
