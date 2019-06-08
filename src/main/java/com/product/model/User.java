package com.product.model;

import com.product.util.TimeUtil;
import com.product.util.page.BasePage;

import java.io.Serializable;
import java.util.Date;

public class User extends BasePage {
    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private String userName;

    /**
     * 
     */
    private String nickName;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }
    public String getCreateTimeStr(){
        return createTime != null ? TimeUtil.getStrByDate(createTime):"";
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}