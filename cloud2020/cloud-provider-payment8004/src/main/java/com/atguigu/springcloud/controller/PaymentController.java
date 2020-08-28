package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author luozhimin
 * @date2020/7/10
 **/
@RestController
@Slf4j
@RequestMapping(value = "/payment")
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    /***
     *
     * 节点为临时性的
     */
    @RequestMapping(value = "/zk")
    public String paymentzk() {
        return "spring-cloud with zookeeper:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
