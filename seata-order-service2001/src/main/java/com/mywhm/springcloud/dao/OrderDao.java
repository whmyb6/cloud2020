package com.mywhm.springcloud.dao;

import com.mywhm.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    //1 新建订单
    void create(Order order);

    //修改订单状态 0-》1
    void update(@Param("userId")Long userId,@Param("status")Integer status);
}
