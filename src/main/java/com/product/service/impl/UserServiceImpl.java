package com.product.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.product.dao.UserDao;
import com.product.model.User;
import com.product.model.UserExample;
import com.product.service.IUserService;
import com.product.util.StringUtil;
import com.product.util.TimeUtil;
import com.product.util.page.PageBean;
import com.product.util.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUserName(String userName) {
        UserExample example=new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<User> users = userDao.selectByExample(example);
        if(users != null && users.size()>0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        userDao.insertSelective(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.selectByExample(new UserExample());
    }

    @Override
    public int updateUserStatus(Integer userId, Integer satsus) {
        User user=new User();
        user.setUserId(userId);
//        user.setStatus(satsus);
        return userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updateUserLoginInfo(Integer userId,String ip) {
        User user=new User();
        user.setUserId(userId);
//        user.setLoginAddr(ip);
//        user.setLoginTime(TimeUtil.getTime());
        userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateUserInfo(User user) {
        return userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public PageBean<User> getUserPage(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(StringUtil.isEmpty(user.getUserName())){
            criteria.andUserNameLike("%%");
        }else {
            criteria.andUserNameLike("%"+user.getUserName()+"%");
        }
        PageHelper.startPage(user.getPageNum(),10);
        List<User> users= userDao.selectByExample(userExample);
        return new PageBean<>(users);
    }
}
