package com.mywhm.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.mywhm.springcloud.dao"})
public class MyBatisConfig {
}
