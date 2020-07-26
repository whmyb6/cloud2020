package com.mywhm.springcloud.service.impl;

import com.mywhm.springcloud.service.PaymentServiceHystrix;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallBackService implements PaymentServiceHystrix {
    @Override
    public String getOK(Integer id) {
        return "--- 消费服务80，getOK 出现问题！ id=" +id + "\t" +Thread.currentThread().getName();
    }

    @Override
    public String getTimeOut(Integer id) {
        return "--- 消费服务80，getTimeout 出现问题！id =" +id+ "\t" +Thread.currentThread().getName();
    }
}
