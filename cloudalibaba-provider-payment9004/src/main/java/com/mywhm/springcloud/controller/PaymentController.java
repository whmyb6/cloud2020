package com.mywhm.springcloud.controller;

import com.mywhm.springcloud.entities.CommonResult;
import com.mywhm.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    private static HashMap<Long,Payment> hashMap =new HashMap<>();
    static {
        hashMap.put(1L, new Payment(1L,"1daxdadadas111111"));
        hashMap.put(2L, new Payment(2L,"2daydadadas222222"));
        hashMap.put(3L, new Payment(3L,"3dazdadadas333333"));
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment =hashMap.get(id);
        CommonResult<Payment> result=new CommonResult<>(200,"fron serverPort:" + serverPort,payment);
        return result;
    }
}
