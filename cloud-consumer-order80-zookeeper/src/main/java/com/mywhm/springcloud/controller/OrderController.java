package com.mywhm.springcloud.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    @Resource
    RestTemplate restTemplate;
    private final static String URI="http://cloud-provider-payment";
    @GetMapping(value = "/consumer/payment/zk")
    public String consumerZkOrder(){
        return restTemplate.getForObject(URI +"/payment/zk",String.class);
    }
}
