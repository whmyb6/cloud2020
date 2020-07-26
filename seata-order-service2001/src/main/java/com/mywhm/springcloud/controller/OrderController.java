package com.mywhm.springcloud.controller;

import com.mywhm.springcloud.domain.CommonResult;
import com.mywhm.springcloud.domain.Order;
import com.mywhm.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;
    //@RequestMapping可以指定GET、POST请求方式
    @RequestMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功！");

    }
}
