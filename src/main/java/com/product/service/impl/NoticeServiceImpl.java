package com.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.product.dao.NoticeDao;
import com.product.model.Notice;
import com.product.model.NoticeExample;
import com.product.service.INoticeService;
import com.product.util.page.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("noticeService ")
public class NoticeServiceImpl implements INoticeService {
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public List<Notice> selectNoticeAll() {
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.setOrderByClause(" create_time desc");
        NoticeExample.Criteria criteria = noticeExample.createCriteria();
        List<Notice> list = noticeDao.selectByExample(noticeExample);
        return list;
    }

    @Override
    public List<Notice> selectNoticeLimit() {
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.setOrderByClause("create_time  desc");
        NoticeExample.Criteria criteria = noticeExample.createCriteria();
        List<Notice> list = noticeDao.selectByExample(noticeExample);
        return list.size() > 3 ?list.subList(0,3):list;
    }

    @Override
    public int addNotice(Notice notice) {
        return noticeDao.insertSelective(notice);
    }

    @Override
    public Notice selectNoticeById(Integer noticeId) {
        Notice notice = noticeDao.selectByPrimaryKey(noticeId);
        return notice;
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeDao.updateByPrimaryKeySelective(notice);
    }

    @Override
    public PageBean<Notice> selectNoticePage(Notice notice) {
        PageHelper.startPage(notice.getPageNum(),10);
        List<Notice> list = selectNoticeAll();
        return new PageBean<Notice>(list);
    }
}
