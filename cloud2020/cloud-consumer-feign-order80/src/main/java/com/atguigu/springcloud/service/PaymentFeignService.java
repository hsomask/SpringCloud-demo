package com.atguigu.springcloud.service;

import com.atguigu.springcloud.bean.CommonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author luozhimin
 * @date2020/7/27
 **/
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @RequestMapping(value = "/payment/getPaymentById", method = RequestMethod.GET)
    //这里使用Param不行，要使用RequestParam
    public CommonResult<?> getPaymentById(@RequestParam(value = "id") Long id);

    @RequestMapping(value = "/payment/paymentFeignTimeout", method = RequestMethod.GET)
    public String paymentFeignTimeout();
}
