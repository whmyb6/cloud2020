package com.mywhm.springcloud.controller;

import com.mywhm.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentServiceController {
    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPost;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result= paymentService.paymentInfoOK(id);
        log.info("paymentInfo_OK:");
        return result;
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        log.info("paymentInfo_Timeout:" +id );
        String result= paymentService.paymentInfo_TimeOut(id);
        log.info("paymentInfo_Timeout:"+ result);
        return result;
    }
    //断路器测试
    @GetMapping("/payment/circuit/{id}")
    public String paymentInfo_Circuit(@PathVariable("id")Integer id){
        return paymentService.payment_CircuitBreak(id);
    }
}
