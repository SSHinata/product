package com.product.model;

import com.product.util.TimeUtil;
import com.product.util.page.BasePage;

import java.io.Serializable;
import java.util.Date;

public class Notice extends BasePage implements Serializable {
    /**
     * 
     */
    private Integer noticeId;

    /**
     * 
     */
    private String noticeTitle;

    /**
     * 
     */
    private String noticeContext;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private String createBy;

    private static final long serialVersionUID = 1L;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
    }

    public String getNoticeContext() {
        return noticeContext;
    }

    public void setNoticeContext(String noticeContext) {
        this.noticeContext = noticeContext == null ? null : noticeContext.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }
    public String getCreateTimeStr(){
        return createTime!=null ? TimeUtil.getStrByDate(createTime):"";
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