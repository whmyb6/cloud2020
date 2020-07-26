package com.mywhm.springcloud.controller;

import com.mywhm.springcloud.service.PaymentServiceHystrix;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "default_FallBack_Handler", commandProperties={@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "8000")})
public class OrderControllerHystrix {
    @Resource
    PaymentServiceHystrix paymentServiceHystrix;

    @GetMapping("/consumer/payment/ok/{id}")
    public String getPaymentOK(@PathVariable("id") Integer id){
        return paymentServiceHystrix.getOK(id);
    }

    //@HystrixCommand(fallbackMethod = "getPaymentTimeOut_Handle",commandProperties = {@HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds" ,value = "3000")})
    @HystrixCommand
    @GetMapping("/consumer/payment/timeout/{id}")
    public String getPaymentTimeOut(@PathVariable("id") Integer id){
        //int age =10/0;
        try {
            TimeUnit.SECONDS.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paymentServiceHystrix.getTimeOut(id);
    }
    public String getPaymentTimeOut_Handle(Integer id){
        return "consumer微服务端80：获取支付业务超时，或者内部错误 ！" + Thread.currentThread().getName();
    }
    public String default_FallBack_Handler(){
        return "consumer全局缺省 微服务端80：获取支付业务超时，或者内部错误 ！" + Thread.currentThread().getName();
    }

}
