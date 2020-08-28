package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.bean.CommonResult;
import com.atguigu.springcloud.bean.PaymentBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author luozhimin
 * @date2020/7/8
 **/
@RestController
@Slf4j
@RequestMapping(value = "/consumer/payment")
public class OrderController {

    //    private final static String PAYMENT_URL = "http://localhost:8001";单击版的把地址写死了
    /*集群版的只用关注eureka的服务名*/
    private final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/create")
    public CommonResult<PaymentBean> create(@RequestBody PaymentBean payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @RequestMapping(value = "/getPaymentById", method = RequestMethod.GET)
    public CommonResult<PaymentBean> getPaymentById(@Param(value = "id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getPaymentById?id=" + id, CommonResult.class, id);
    }

}
