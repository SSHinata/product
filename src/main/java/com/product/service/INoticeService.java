package com.product.service;

import com.product.model.Notice;
import com.product.util.page.PageBean;

import java.util.List;

public interface INoticeService {
    List<Notice> selectNoticeAll();
    List<Notice> selectNoticeLimit();

    int addNotice(Notice notice);

    Notice selectNoticeById(Integer noticeId);

    int updateNotice(Notice notice);

    PageBean<Notice> selectNoticePage(Notice notice);
}
