package com.mywhm.springcloud.service;

import com.mywhm.springcloud.service.impl.PaymentFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallBackService.class)
public interface PaymentServiceHystrix {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String getOK(@PathVariable("id") Integer id);
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String getTimeOut(@PathVariable("id") Integer id);

}