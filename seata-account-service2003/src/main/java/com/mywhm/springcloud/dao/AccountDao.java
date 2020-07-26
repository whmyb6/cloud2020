package com.mywhm.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {
    //扣减账户额度
    public void decrease(@Param("userId")Long userId , @Param("money")BigDecimal money);
}
