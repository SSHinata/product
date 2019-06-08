package com.product.service;


import com.product.model.Orders;
import com.product.util.page.PageBean;

import java.util.List;

public interface IOrderService {

    public int update(Integer orderId, Integer status);

    public Orders carAdd(Integer userId);

    public List<Orders> getUserOrder(Integer userId);

    public Orders productAdd(Integer userId, Integer productId);

    Integer deleteOrderById(Integer orderId);

    List<Orders> getOrderAll();

    PageBean<Orders> selectOrderPage(Orders orders);
}
