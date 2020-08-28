package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

/**
 * @author luozhimin
 * @date2020/7/28
 **/
@Service
public class PaymentService {
    /**
     * 正常访问的方法
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "   paymentInfo_OK,id:" + id + " 正常访问！";
    }

    /**
     * 模拟超时的服务降级
     * （3s是正常的，现在已经5s了）
     *
     * @param id
     * @return
     */
    //服务降级注解，如果该方法出现了问题，要有一个兜底的方法(该注解是配置在方法上面的)
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    public String paymentInfo_TimeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int i = 10 / 0;
        return "线程池： " + Thread.currentThread().getName() + "   paymentInfo_TimeOut,id:" + id + " 耗时5s！";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "   paymentInfo_TimeOutHandler,id:" + id + " 8001系统繁忙或出错了o(╥﹏╥)o";
    }

    //------------------------分割线--------------------------------------------
    //========================服务熔断=========================================

    /**
     * 在窗口期时，十次调用接口，有6次以上失败，就开启断路器，
     * 然后在下一个窗口期进行调用的时候，如果调用成功，就会逐步关闭断路器（从半关闭->关闭，
     * 正确率高了，开始半关闭状态，然后再到关闭状态）
     * <p>
     * 断路器开启的时候，再有请求进来的时候，这时候不会调用主逻辑，而是会调用fallback方法。
     * 通过断路器，实现了主动将主逻辑降为fallback的方法，减少延迟等待的时间。
     * <p>
     * 如何恢复主逻辑：熔断之后，hystrix会开启一个休眠窗口期，在这个时间内，fallback方法会暂时
     * 成为主逻辑方法，当休眠窗口期时间到了之后，断路器处于半关闭状态，会将发送的请求转发到主逻辑方法上，
     * 如果请求正常返回，断路器就关闭；如果不正常的话，断路器继续开启，又重新进入一个休眠窗口期
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),              //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),    //请求数达到后才计算
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),  //错误率达到多少跳闸
    })
    public String paymentCircuitBreaker(@RequestParam("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@RequestParam("id") Integer id) {
        return "id 不能为负数,请稍后再试， o(╥﹏╥)o id: " + id;
    }

}
