package com.mywhm.springcloud.controller;

import cn.hutool.core.date.DateTime;
import com.mywhm.springcloud.entities.CommonResult;
import com.mywhm.springcloud.entities.Payment;
import com.mywhm.springcloud.lb.LBImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    @Resource
    DiscoveryClient discoveryClient;
    @Resource
    LBImpl lbImpl;

    //public static final String PAYMENT_URI="http://localhost:8001";

    public static final String HOST_URI="http://CLOUD-PROVIDER-PAYMENT-SERVICE";
    public static final String ServiceId="CLOUD-PROVIDER-PAYMENT-SERVICE";
    public String getURI(){
        // 设置是否在 RestTemplate 组件上，加上 @LoadBalanced 标签======
        boolean LoadBalanced = true;
        List<ServiceInstance> instances = discoveryClient.getInstances(ServiceId);
        if(instances ==null || instances.size() <=0){
            return null;
        }
        ServiceInstance serviceInstance=  lbImpl.instances(instances);
        URI uri = serviceInstance.getUri();
        log.info("***** getPaymentLB= " + uri);
        return LoadBalanced ? HOST_URI: uri.toString();

    }

    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(getURI() + "/payment/create", payment,CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        log.info("get data! " + new DateTime());
        return   restTemplate.getForObject(getURI() + "/payment/get/"+id,CommonResult.class );
    }
    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        return restTemplate.getForObject(getURI()+"/payment/lb",String.class);
    }
}
