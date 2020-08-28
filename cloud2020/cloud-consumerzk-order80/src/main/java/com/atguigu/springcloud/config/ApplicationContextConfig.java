package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author luozhimin
 * @date2020/7/8
 * function:RestTemplate是Spring用于同步client端的核心类，
 * 简化了与http服务的通信，并满足RestFul原则，程序代码可以给它提供URL，并提取结果
 **/
@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced //负载均衡，自动去选择服务
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
