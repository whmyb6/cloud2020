package com.mywhm.springcloud.controller;

import com.mywhm.springcloud.entities.CommonResult;
import com.mywhm.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    PaymentFeignService paymentFeignService;
    @GetMapping(value ="/consumer/payment/get/{id}")
    public CommonResult query(@PathVariable("id") Long id){
        return paymentFeignService.query(id);
    }
    @GetMapping(value = "/consumer/payment/lb/timeout")
    public String getPaymentLBTimeout(){
        return paymentFeignService.getPaymentLBTimeout();
    }

}
