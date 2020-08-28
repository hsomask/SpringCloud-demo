package com.atguigu.springcloud.service;

import com.atguigu.springcloud.bean.PaymentBean;
import com.atguigu.springcloud.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luozhimin
 * @date2020/7/7
 **/
@Service
public class PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    public int create(PaymentBean payment) {
        return paymentMapper.create(payment);
    }

    public PaymentBean getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
