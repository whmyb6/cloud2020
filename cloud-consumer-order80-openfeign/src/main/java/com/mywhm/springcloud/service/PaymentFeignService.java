package com.mywhm.springcloud.service;

import com.mywhm.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/get/{id}")
    public CommonResult query(@PathVariable("id") Long id);
    @GetMapping(value = "/payment/lb/timeout")
    public String getPaymentLBTimeout();
}
