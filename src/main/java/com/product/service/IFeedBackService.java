package com.product.service;

import com.product.model.Feedback;
import com.product.util.page.PageBean;

import java.util.List;

public interface IFeedBackService {
    List<Feedback> selectFeedBackAll();


    int addFeedBack(Feedback feedback);

    PageBean<Feedback> selectFeedBackPage(Feedback feedback);

}
