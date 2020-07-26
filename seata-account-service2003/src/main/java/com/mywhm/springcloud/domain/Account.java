package com.mywhm.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private Long userId;  //用户ID
    private BigDecimal total; //总额度
    private BigDecimal used;  //已用额度
    private BigDecimal residue; //剩余额度
}
