package com.product.model;

import com.product.util.page.BasePage;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product extends BasePage implements Serializable {
    /**
     * 
     */
    private Integer productId;

    /**
     * 
     */
    private String productName;

    /**
     * 
     */
    private BigDecimal productPrice;

    /**
     * 1 上架  0 下架
     */
    private Integer productStatus;

    /**
     * 
     */
    private String imgUrl;

    /**
     * 
     */
    private String imgName;

    /**
     * 
     */
    private String remark;

    /**
     * 销售额
     */
    private Integer salesNumber;

    private static final long serialVersionUID = 1L;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName == null ? null : imgName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(Integer salesNumber) {
        this.salesNumber = salesNumber;
    }
}