package com.mywhm.springcloud.service;

import com.mywhm.springcloud.entities.CommonResult;
import com.mywhm.springcloud.entities.Payment;
import com.mywhm.springcloud.service.impl.PaymentFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//当访问微服务nacos-payment-provider ，出现问题时，采用PaymentFallbackServiceImpl类定义，对系统进行保护
@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackServiceImpl.class )
public interface PaymentService {
    @GetMapping(value = "/payment/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
