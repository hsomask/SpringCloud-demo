package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author luozhimin
 * @date2020/7/29 集中处理服务降级
 **/
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "--------PaymentFallbackService  fall back :paymentInfo_OK ";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "--------PaymentFallbackService  fall back :paymentInfo_Timeout ";
    }


}
