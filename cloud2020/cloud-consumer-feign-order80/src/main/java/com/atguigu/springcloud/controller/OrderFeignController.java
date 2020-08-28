package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.bean.CommonResult;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luozhimin
 * @date2020/7/27
 **/
@RestController
@Slf4j

public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @RequestMapping(value = "/consumer/payment/getPaymentById", method = RequestMethod.GET)
    public CommonResult<?> getPaymentById(@RequestParam(value = "id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @RequestMapping(value = "/consumer/payment/paymentFeignTimeout", method = RequestMethod.GET)
    public String paymentFeignTimeout() {
        //openFeign-ribbon:客户端默认等待1s
        return paymentFeignService.paymentFeignTimeout();
    }
}
