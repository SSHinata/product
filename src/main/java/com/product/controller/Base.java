package com.product.controller;

import com.product.model.Manager;
import com.product.model.User;

import javax.servlet.http.HttpServletRequest;

public class Base {
    public void setUserSession(User user,HttpServletRequest request){
        request.getSession().setAttribute("user",user);
    }

    public void setManagerSession(Manager manager,HttpServletRequest request){
        request.getSession().setAttribute("manager",manager);
    }

    public User getUser(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return user;
    }

    public Manager getManager(HttpServletRequest request){
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        return manager;
    }
}
