package com.mywhm.springcloud.service.impl;

import com.mywhm.springcloud.service.PaymentService;
import com.mywhm.springcloud.entities.Payment;
import com.mywhm.springcloud.Dao.PaymentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
