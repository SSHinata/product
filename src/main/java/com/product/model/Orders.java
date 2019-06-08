package com.product.model;

import com.product.util.TimeUtil;
import com.product.util.page.BasePage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Orders extends BasePage implements Serializable {
    /**
     * 
     */
    private Integer ordersId;

    /**
     * 
     */
    private String orderCode;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 状态  1未付款 2付款完成
     */
    private Integer status;

    /**
     * 
     */
    private Date payTime;

    /**
     * 
     */
    private BigDecimal sumMoney;

    /**
     * 是否删除  0是  1否
     */
    private Integer isDelete;

    private List<OrderItem> items;

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    private static final long serialVersionUID = 1L;

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public String getCreateTimeStr(){
        return createTime!=null? TimeUtil.getStrByDate(createTime):"";
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPayTime() {
        return payTime;
    }
    public String getpayTimeStr(){
        return payTime!=null? TimeUtil.getStrByDate(payTime):"";
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(BigDecimal sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}