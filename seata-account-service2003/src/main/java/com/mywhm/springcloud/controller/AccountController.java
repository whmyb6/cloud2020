package com.mywhm.springcloud.controller;

import com.mywhm.springcloud.domain.CommonResult;
import com.mywhm.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@Slf4j
public class AccountController {
    @Resource
    private AccountService accountService;
    //@RequestMapping可以指定GET、POST请求方式
    @RequestMapping("/account/decrease")
    public CommonResult decrease(Long userId,BigDecimal money){
        accountService.decrease(userId, money);
        return new CommonResult(200,"账户余额扣减完成！");
    }
}
