package com.mywhm.springcloud.myhandller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mywhm.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handleException1(BlockException exception){
        return new CommonResult(4444,"自定义的信息,Gloabal handlerException.....CustomerBlockHandler--1");
    }
    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(4444,"自定义的处理信息,Gloabal handlerException.....CustomerBlockHandler--2");
    }
}
