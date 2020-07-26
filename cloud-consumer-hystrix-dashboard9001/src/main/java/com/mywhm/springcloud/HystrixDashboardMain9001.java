package com.mywhm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

//打开监视界面
//http://localhost:9001/hystrix
//输入被监控的微服务地址
//http://localhost:8001/hystrix.stream
//开始测试
//断路器关闭测试
//http://localhost:8001/payment/circuit/1
//断路器开启测试
//http://localhost:8001/payment/circuit/-1


@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class,args);
    }


}
