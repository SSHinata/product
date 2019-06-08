package com.product.service;

import com.product.model.User;
import com.product.util.page.PageBean;

import java.util.List;

public interface IUserService {
    public User getUserByUserName(String userName);
    public void addUser(User user);
    List<User> getAllUser();

    int  updateUserStatus(Integer userId, Integer satsus);

    void updateUserLoginInfo(Integer userId, String ip);

    int updateUserInfo(User user);

    PageBean<User> getUserPage(User user);
}
