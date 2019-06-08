package com.product.model;

import com.product.util.TimeUtil;
import com.product.util.page.BasePage;

import java.io.Serializable;
import java.util.Date;

public class Manager extends BasePage implements Serializable {
    /**
     * 
     */
    private Integer managerId;

    /**
     * 
     */
    private String managerName;

    /**
     * 
     */
    private String password;

    /**
     * 状态   1 可用  0 不可用
     */
    private Integer status;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String createBy;

    private static final long serialVersionUID = 1L;

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public String getCreateTimeStr(){
        return createTime !=null? TimeUtil.getStrByDate(createTime):"";
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }
}