package com.atguigu.springcloud.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luozhimin
 * @date2020/7/8
 * function:这个common类是将各个模块都会使用到的东西抽取出来，然后其他模块在pom文件中引入之后，可以直接用，减少代码的冗余
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>{
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
