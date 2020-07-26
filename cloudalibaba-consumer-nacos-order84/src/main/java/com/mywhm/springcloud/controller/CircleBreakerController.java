package com.mywhm.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mywhm.springcloud.entities.CommonResult;
import com.mywhm.springcloud.entities.Payment;
import com.mywhm.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {

    private static final String SERVICE_URI="http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/{id}")
    //fallback 只负责业务异常处理====
    //blockHandler 只负责sentinel控制台配置违规
    @SentinelResource(value = "payment" ,fallback = "handler_Fallback"
                                        ,blockHandler = "handler_Block"
    )
    public CommonResult<Payment> getPayment(@PathVariable Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URI+"/payment/"+id,CommonResult.class,id);
        if(id ==4){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常.......");
        }else if (result.getData()==null){
            throw new NullPointerException("NullPointerException,该ID没有相应的记录，空指针异常.....");
        }
        return result;
    }

    public CommonResult<Payment> handler_Fallback(@PathVariable Long id,Throwable e ){
        Payment payment =new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常处理 handler_Fallback，exception= " +e.getMessage(),payment);

    }
    public CommonResult<Payment> handler_Block(@PathVariable Long id, BlockException e){
        Payment payment =new Payment(id,"null");
        return new CommonResult<>(445,"兜底限流处理 handler_Block，exception= " +e.toString(),payment);
    }

    //============ OpenFeign 采用openfeign组件访问=======
    @Resource
    private PaymentService paymentService;
    @GetMapping("/consumer/paymentSQL/{id}")
    @SentinelResource(value = "consumer-paymentSQL",fallback = "handler_Fallback",blockHandler = "handler_Block")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        CommonResult<Payment> result = paymentService.paymentSQL(id);
        if(id ==4){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常.......");
        }else if (result.getData()==null){
            throw new NullPointerException("NullPointerException,该ID没有相应的记录，空指针异常.....");
        }
        return result;

    }

}
