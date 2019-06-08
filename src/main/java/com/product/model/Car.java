package com.product.model;

import com.product.util.page.BasePage;

import java.io.Serializable;

public class Car extends BasePage {
    /**
     * 
     */
    private Integer carId;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer productId;

    /**
     * 
     */
    private Integer number;

    private String productName;
    private String imgName;
    private Double price;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    private static final long serialVersionUID = 1L;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}