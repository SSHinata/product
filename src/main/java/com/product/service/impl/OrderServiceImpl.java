package com.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.product.dao.CarDao;
import com.product.dao.OrdersDao;
import com.product.dao.OrderItemDao;
import com.product.dao.ProductDao;
import com.product.model.Car;
import com.product.model.CarExample;
import com.product.model.Orders;
import com.product.model.OrdersExample;
import com.product.model.OrderItem;
import com.product.model.Product;
import com.product.service.ICarService;
import com.product.service.IOrderService;
import com.product.util.TimeUtil;
import com.product.util.page.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("orderService")
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrdersDao orderDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ICarService carService;


    @Override
    public int update(Integer orderId, Integer status) {
        Orders order = new Orders();
        order.setOrdersId(orderId);
        order.setStatus(status);
        order.setPayTime(new Date());
        int i = orderDao.updateByPrimaryKeySelective(order);

        //修改产品销售额
        List<OrderItem> orderItems = orderItemDao.selectByOrderId(orderId);
        for(OrderItem orderItem:orderItems){
            Product product = productDao.selectByPrimaryKey(orderItem.getProductId());
            product.setSalesNumber(product.getSalesNumber()+orderItem.getNumber());
            productDao.updateByPrimaryKeySelective(product);
        }
        return  i;
    }

    //创建订单-目前购物车全部结算
    @Override
    public Orders carAdd(Integer userId) {
        //查询用户下的购物车
        CarExample example = new CarExample();
        CarExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        //创建order
        Orders order=new Orders();
        order.setUserId(userId);
        order.setStatus(1);
        order.setCreateTime(new Date());
        order.setOrderCode(TimeUtil.getTimeCode());
        orderDao.insertSelective(order);

        List<Car> carList = carDao.selectByExample(example);
        BigDecimal sumMoney= new BigDecimal(BigInteger.ZERO);
        //创建orderItem
        List<OrderItem> list=new ArrayList<OrderItem>();
        for(Car car:carList){
            OrderItem item=new OrderItem();
            item.setOrderId(order.getOrdersId());
            item.setProductId(car.getProductId());
            item.setNumber(car.getNumber());
            //根据productId查价格
            Product product = productDao.selectByPrimaryKey(car.getProductId());

            //计算小计
            BigDecimal subtotal = product.getProductPrice().multiply(new BigDecimal(car.getNumber()));
            item.setSubtotal(subtotal);
            item.setPrice(product.getProductPrice());
            item.setProductName(product.getProductName());
            item.setImgName(product.getImgName());
            sumMoney = sumMoney.add(subtotal);
            orderItemDao.insert(item);
            list.add(item);
        }
        //修改order的sumMoney
        order.setSumMoney(sumMoney);
        orderDao.updateByPrimaryKeySelective(order);
        order.setItems(list);

        //订单创建完清空购物车
        carService.deleteUserAll(userId);
        return order;

    }


    @Override
    public Orders productAdd(Integer userId, Integer productId) {
        Product product = productDao.selectByPrimaryKey(productId);

        Orders order=new Orders();
        order.setOrderCode(TimeUtil.getTimeCode());
        order.setStatus(1);
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setSumMoney(product.getProductPrice());
        orderDao.insertSelective(order);

        OrderItem orderItem=new OrderItem();
        orderItem.setNumber(1);
        orderItem.setProductId(productId);
        orderItem.setOrderId(order.getOrdersId());
        orderItem.setSubtotal(product.getProductPrice());
        orderItemDao.insertSelective(orderItem);

        orderItem.setProductName(product.getProductName());
        orderItem.setPrice(product.getProductPrice());
        orderItem.setImgName(product.getImgName());

        List<OrderItem> list=new ArrayList<OrderItem>();
        list.add(orderItem);
        order.setItems(list);
        return order;
    }

    @Override
    public Integer deleteOrderById(Integer orderId) {
        Orders order = new Orders();
        order.setOrdersId(orderId);
        order.setIsDelete(0);//更改删除状态
        int i = orderDao.updateByPrimaryKeySelective(order);
        return i;
    }

    @Override
    public List<Orders> getOrderAll() {
        OrdersExample example = new OrdersExample();
        example.setOrderByClause(" create_time desc");
        OrdersExample.Criteria criteria = example.createCriteria();
        List<Orders> orders = orderDao.selectByExample(example);
        if(orders != null && orders.size() > 0){
            for(Orders order:orders){
                List<OrderItem> orderItems = orderItemDao.selectByOrderId(order.getOrdersId());
                order.setItems(orderItems);
            }
        }
        return orders;
    }

    @Override
    public PageBean<Orders> selectOrderPage(Orders orders) {
        PageHelper.startPage(orders.getPageNum(),10);
        List<Orders> orderAll = getOrderAll();
        return new PageBean<Orders>(orderAll);
    }

    @Override
    public List<Orders> getUserOrder(Integer userId) {
        OrdersExample example=new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIsDeleteEqualTo(1);
        example.setOrderByClause("orders_id desc");
        List<Orders> orders = orderDao.selectByExample(example);
        if(orders != null && orders.size() > 0){
            for(Orders order:orders){
                List<OrderItem> orderItems = orderItemDao.selectByOrderId(order.getOrdersId());
                order.setItems(orderItems);
            }
        }
        return orders;
    }
}
