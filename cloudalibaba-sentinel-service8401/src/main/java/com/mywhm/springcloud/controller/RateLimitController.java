package com.mywhm.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mywhm.springcloud.entities.CommonResult;
import com.mywhm.springcloud.entities.Payment;
import com.mywhm.springcloud.myhandller.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler="handleException")
    public CommonResult byResource(){
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException exception){
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }
    @GetMapping("/byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200,"按URL名称限流测试OK",new Payment(2020L,"serial002"));
    }
    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handleException2")
    public CommonResult customerBlockHandle(){
        return new CommonResult(200,"按客户自定义",new Payment(2020L,"serial003"));
    }

}
