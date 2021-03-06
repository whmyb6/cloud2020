package com.mywhm.springcloud.controller;

import com.mywhm.springcloud.entities.CommonResult;
import com.mywhm.springcloud.entities.Payment;
import com.mywhm.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    PaymentService paymentService;
    @Value("${server.port}")
    private String ServerPort;


    // 为 cloud-consumer 微服务访问
    // 访问地址：IE  http://localhost/consumer/payment/create?serial=123fsdf
    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        log.info(" *********** create " + payment.toString());
        int result = paymentService.create(payment);
        log.info("*****插入数据：" + result);
        if (result> 0){
            return new CommonResult(200,"插入数据正常 server.port= " + ServerPort ,result);
        }else{
            return new CommonResult(444,"插入数据异常",result);

        }
    }

    // 为 cloud-gateway 微服务访问
    // 访问地址： Postman  http://localhost:9527/payment/create?serial=123fsdf
    @PostMapping("/create/gateway")
    public CommonResult createGateway(Payment payment){
        log.info(" *********** createGateway " + payment.toString());
        int result = paymentService.create(payment);
        log.info("*****插入数据：" + result);
        if (result> 0){
            return new CommonResult(200,"插入数据正常 server.port= " + ServerPort ,result);
        }else{
            return new CommonResult(444,"插入数据异常",result);

        }
    }

    @GetMapping("/get/{id}")
    public CommonResult query(@PathVariable("id") Long id){
        Payment payment=paymentService.getPaymentById(id);
        log.info("**** 查询结果 ：" + payment + " ！server.port= " + ServerPort );
        if(payment !=null)
            return new CommonResult(200,"ok server.port= " + ServerPort ,payment);
        else
            return new CommonResult(444,"没有查询到记录，id=" + id ,null);
    }
    @GetMapping(value = "/lb")
    public String getPaymentLB(){
        return ServerPort;
    }

    @GetMapping(value = "/lb/timeout")
    public String getPaymentLBTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ServerPort;
    }
}
