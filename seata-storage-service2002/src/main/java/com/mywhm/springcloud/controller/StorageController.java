package com.mywhm.springcloud.controller;

import com.mywhm.springcloud.domain.CommonResult;
import com.mywhm.springcloud.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StorageController {
    @Resource
    private StorageService storageService;
    //扣减库存
    //@RequestMapping可以指定GET、POST请求方式
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId,Integer count){
        storageService.decrease(productId, count);
        return  new CommonResult(200,"扣减库存成功！");
    }
}
