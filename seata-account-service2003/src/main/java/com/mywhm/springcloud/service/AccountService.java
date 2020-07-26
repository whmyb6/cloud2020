package com.mywhm.springcloud.service;

import java.math.BigDecimal;

public interface AccountService {
    //扣减用户余额
    void decrease(Long userId, BigDecimal money);
}
