package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luozhimin
 * @date2020/7/28
 **/
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        //返回的日志信息包括头部，还有带的正文信息
        return Logger.Level.FULL;
    }
}
