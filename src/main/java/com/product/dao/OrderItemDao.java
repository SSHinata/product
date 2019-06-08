package com.product.dao;

import com.product.model.OrderItem;
import com.product.model.OrderItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemDao {
    int countByExample(OrderItemExample example);

    int deleteByExample(OrderItemExample example);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    List<OrderItem> selectByExample(OrderItemExample example);

    int updateByExampleSelective(@Param("record") OrderItem record, @Param("example") OrderItemExample example);

    int updateByExample(@Param("record") OrderItem record, @Param("example") OrderItemExample example);

    List<OrderItem> selectByOrderId(Integer ordersId);
}