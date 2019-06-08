package com.product.model;

import com.product.util.TimeUtil;
import com.product.util.page.BasePage;

import java.io.Serializable;
import java.util.Date;

public class Feedback extends BasePage  {
    /**
     * 
     */
    private Integer feedbackId;

    /**
     * 
     */
    private String feedbackContext;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String createBy;

    private static final long serialVersionUID = 1L;

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackContext() {
        return feedbackContext;
    }

    public void setFeedbackContext(String feedbackContext) {
        this.feedbackContext = feedbackContext == null ? null : feedbackContext.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getCreateTimeStr() {
       return createTime!=null? TimeUtil.getStrByDate(createTime):"";
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }
}