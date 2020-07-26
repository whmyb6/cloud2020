package com.mywhm.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.mywhm.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfoOK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfoOK id=" +id + " OK!" ;
    }

    @HystrixCommand(fallbackMethod = "payment_TimeOutHandler" ,commandProperties = {@HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds" ,value = "5000")})
    @Override
    public String paymentInfo_TimeOut(Integer id) {
        //int x = 5/0;
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo_TimeOut id=" +id + " BAD!" ;
    }
    public String payment_TimeOutHandler(Integer id){
        return "payment-hystrix 服务端:调用支付接口超时或异常: " + "当前线程池名称：" +Thread.currentThread().getName();
    }

    // 服务熔断

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_FallBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //开启端断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //错误请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 快照时间窗
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") // 失败率
    })
    public String payment_CircuitBreak(Integer id){
        if(id < 0){
            throw new RuntimeException("******* id 不能为负数! id=" + id);

        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t调用成功，流水号：" + serialNumber;
    }
    public String paymentCircuitBreaker_FallBack(Integer id){
        return "id 不能为负数，请重新再试， id:" +id  +"\t" + Thread.currentThread().getName() ;
    }
}
