package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luozhimin
 * @date2020/7/28
 **/
@RestController
@Slf4j
//默认服务降级调用的方法，如果有特殊处理的话,就在特殊处理的方法上加HystrixCommand
//@DefaultProperties(defaultFallback = "globalExceptionHandler")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/consumer/payment/hystrix/ok", method = RequestMethod.GET)
    public String paymentInfo_OK(@RequestParam("id") Integer id) {
        return paymentService.paymentInfo_OK(id);
    }

    /**
     * 客户端服务降级
     *
     * @param id
     * @return
     */
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})
    @RequestMapping(value = "/consumer/payment/hystrix/timeout", method = RequestMethod.GET)
//    @HystrixCommand //加上这个注解，就是服务降级处理，有兜底的方法。默认调用globalExceptionHandler
    public String paymentInfo_Timeout(@RequestParam("id") Integer id) {
        return paymentService.paymentInfo_Timeout(id);
    }

//    public String paymentInfo_TimeOutHandler(Integer id) {
//        return "线程池： " + Thread.currentThread().getName() + "   paymentInfo_TimeOutHandler,id:" + id + " 对方系统繁忙或自身系统出错了o(╥﹏╥)o";
//    }
//
//    public String globalExceptionHandler() {
//        return "系统繁忙或者出错，请稍后再试";
//    }


    /**
     * 服务降级：
     * 定义：当服务超时或者运行报错的时候，客户端陷入等待，这个时候就要定义一个服务降级方法（也就是兜底方法）
     * 让服务从当前的错误状态降级到一个不错误的状态
     * 方法：（一般都是在客户服务做降级操作，也可以在提供端做降级服务）
     * 提供端降级服务：
     * 1.定义降级的方法
     * 2.在需要使用服务降级的方法上加上注解
     * @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
     *
     * 客户端降级服务：
     * 1.在application.yml里配置
     * feign:
     *   hystrix:
     *     enabled: true
     * 2.在类 处加上注解（分为两种注解，一种为特殊定制注解，一种为全局注解）
     * 全局注解加上后，还要在方法上加上@HystrixCommand，才能识别到这个方法需要服务降级
     */
}
