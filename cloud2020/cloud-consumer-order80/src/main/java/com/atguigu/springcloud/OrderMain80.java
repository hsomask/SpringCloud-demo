package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author luozhimin
 * @date2020/7/8
 **/
@SpringBootApplication
@EnableEurekaClient
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}

/**
 * 自我保护机制是应对网络异常的安全保护措施，设计思想是在短时间内（90s）内丢失了大量实例的心跳，
 * 这个时候eureka会开启自我保护机制，不会剔除该服务的信息
 * */