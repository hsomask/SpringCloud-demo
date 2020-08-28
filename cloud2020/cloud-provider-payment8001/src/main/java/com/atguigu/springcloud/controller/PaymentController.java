package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.bean.CommonResult;
import com.atguigu.springcloud.bean.PaymentBean;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


/**
 * @author luozhimin
 * @date2020/7/7
 **/
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody PaymentBean payment) {
        int result = paymentService.create(payment);
        log.info("*******插入结果为: " + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    //    @GetMapping("/getPaymentById/{id}")
    @RequestMapping(value = "/getPaymentById", method = RequestMethod.GET)
    public CommonResult<?> getPaymentById(@Param(value = "id") Long id) {
        PaymentBean paymentById = paymentService.getPaymentById(id);
        log.info("*********查找结果为: " + paymentById);
        if (paymentById != null) {
            return new CommonResult(200, "查找成功,serverPort" + serverPort, paymentById);
        } else {
            return new CommonResult(444, "查找为空,查找ID为: " + id, null);
        }

    }

    @RequestMapping(value = "/lb", method = RequestMethod.GET)
    public String getPaymentLB() {
        return serverPort;
    }

    /**
     * 模拟openFeign超时
     */
    @RequestMapping(value = "/paymentFeignTimeout", method = RequestMethod.GET)
    public String paymentFeignTimeout() {
        try {
            //强制休息3秒钟
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
