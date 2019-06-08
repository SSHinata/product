package com.product.model;

import com.product.util.page.BasePage;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItem extends BasePage implements Serializable {
    /**
     * 
     */
    private Integer orderId;

    /**
     * 
     */
    private Integer productId;

    /**
     * 购买数量
     */
    private Integer number;

    /**
     * 单种商品价格小计
     */
    private BigDecimal subtotal;

    private String productName;
    private String imgName;
    private BigDecimal price;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private static final long serialVersionUID = 1L;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}