package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author luozhimin
 * @date2020/7/28
 **/
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackService.class)
public interface PaymentService {
    @RequestMapping(value = "/payment/hystrix/ok", method = RequestMethod.GET)
    public String paymentInfo_OK(@RequestParam("id") Integer id);

    @RequestMapping(value = "/payment/hystrix/timeout", method = RequestMethod.GET)
    public String paymentInfo_Timeout(@RequestParam("id") Integer id);

    /**
     * 使用feign服务的时候，在@FeignClient上面有个属性是fallback
     * 定义一个方法继承这个类，然后就可以重写这个类的方法
     * 在这个类出错的时候，就可以调用PaymentFallbackService里面对应的方法
     */
}
