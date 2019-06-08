package com.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.product.dao.FeedbackDao;
import com.product.model.Feedback;
import com.product.model.FeedbackExample;
import com.product.service.IFeedBackService;
import com.product.util.page.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("feedBackService")
public class FeedBackServiceImpl implements IFeedBackService {
    @Autowired
    private FeedbackDao feedbackDao;
    @Override
    public List<Feedback> selectFeedBackAll() {
        FeedbackExample feedbackExample = new FeedbackExample();
        feedbackExample.setOrderByClause(" create_time desc ");
        FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
        List<Feedback> feedbacks = feedbackDao.selectByExample(feedbackExample);
        return feedbacks;
    }

    @Override
    public int addFeedBack(Feedback feedback) {
        return feedbackDao.insertSelective(feedback);
    }

    @Override
    public PageBean<Feedback> selectFeedBackPage(Feedback feedback) {
        PageHelper.startPage(feedback.getPageNum(),10);
        List<Feedback> feedbacks =selectFeedBackAll();
        return new PageBean<Feedback>(feedbacks);
    }
}
