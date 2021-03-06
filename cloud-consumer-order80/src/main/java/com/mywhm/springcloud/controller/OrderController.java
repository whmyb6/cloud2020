package com.mywhm.springcloud.controller;

import com.mywhm.springcloud.entities.CommonResult;
import com.mywhm.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    //public static final String PAYMENT_URI="http://localhost:8001";
    public static final String PAYMENT_URI="http://CLOUD-PROVIDER-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URI + "/payment/create", payment,CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URI + "/payment/get/"+id,CommonResult.class );
    }
}
