package com.atguigu.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author luozhimin
 * @date2020/7/7
 **/
@SpringBootApplication
@Slf4j
@EnableEurekaClient
//@EnableDiscoveryClient
public class PaymentMain8001  {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
