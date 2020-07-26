package com.mywhm.springcloud.service.impl;

import com.mywhm.springcloud.dao.OrderDao;
import com.mywhm.springcloud.domain.Order;
import com.mywhm.springcloud.service.AccountService;
import com.mywhm.springcloud.service.OrderService;
import com.mywhm.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;



    /*
    *   创建订单-》调用库存服务 扣减库存 =》 调用账户服务 扣减账户余额-》修改订单状态
    *
    *    下订单-》减库存-》减余额-》改状态
    *
    * */
    @Override
    /*
    *   name 自定义，保证唯一性即可
    *   name 自定义，保证唯一性即可
    *   rollbackFor = Exception.class  出现Exception异常，发生回滚
    *  */
    @GlobalTransactional(name = "whm-create-order" ,rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("-------->开始新建订单");
        orderDao.create(order);
        log.info("------------->订单微服务开始调库存，做减法 ");
        storageService.decrease(order.getProductId(),order.getCount());

        log.info("-------------->订单微服务开始调用账户，做减法");
        accountService.decrease(order.getUserId(),order.getMoney());

        log.info("------------->修改订单状态 0 --> 1 开始");
        orderDao.update(order.getUserId(),0);
        log.info("====== 下订单，完成 =======！");


    }
}
