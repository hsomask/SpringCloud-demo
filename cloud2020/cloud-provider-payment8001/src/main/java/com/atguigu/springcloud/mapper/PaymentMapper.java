package com.atguigu.springcloud.mapper;

import com.atguigu.springcloud.bean.PaymentBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author luozhimin
 * @date2020/7/8
 **/
@Mapper
public interface PaymentMapper  {
    @Insert("insert into payment(serial) values(#{serial})")
    int create(PaymentBean payment);

    @Select("select * from payment where id=#{id}")
    PaymentBean getPaymentById(@Param("id") Long id);
}
