package com.mywhm.springcloud;

import com.mywhm.springcloud.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="CLOUD-PROVIDER-PAYMENT-SERVICE",configuration = MySelfRule.class)
public class OrderMain80Random {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80Random.class,args );
    }
}
