package com.atguigu.springcloud.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author luozhimin
 * @date2020/7/8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentBean implements Serializable {
    @TableId(type= IdType.AUTO)
    private  Long id;
    private  String serial;
}
