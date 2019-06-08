package com.product.service;

import com.product.model.Manager;
import com.product.util.page.PageBean;

import java.util.List;

public interface IManagerService {
    public Manager getManager(String managerName, String password);

    PageBean<Manager> selectManagerPage(Manager manager);

    Manager selectManagerById(Integer managerId);

    int updateManager(Manager manager);

    List<Manager> getManagerByName(String managerName);

    int insertManager(Manager manager);

    int deleteById(Integer managerId);
}
