package com.mywhm.springcloud.service.impl;

import com.mywhm.springcloud.entities.CommonResult;
import com.mywhm.springcloud.entities.Payment;
import com.mywhm.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackServiceImpl implements PaymentService {
    //采用openfeign 对熔断进行保护， 兜底函数定义
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回------PaymentFallbackServiceImpl",new Payment(id,"错误ID"));
    }
}
