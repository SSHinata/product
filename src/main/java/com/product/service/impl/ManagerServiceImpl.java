package com.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.product.dao.ManagerDao;
import com.product.model.Manager;
import com.product.model.ManagerExample;
import com.product.service.IManagerService;
import com.product.util.page.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("managerService")
public class ManagerServiceImpl implements IManagerService {
    @Autowired
    private ManagerDao managerDao;

    @Override
    public Manager getManager(String managerName,String password) {
        ManagerExample example=new ManagerExample();
        ManagerExample.Criteria criteria = example.createCriteria();
        criteria.andManagerNameEqualTo(managerName);
        criteria.andPasswordEqualTo(password);
        List<Manager> managerList = managerDao.selectByExample(example);
        if(managerList != null && managerList.size() > 0){
            return managerList.get(0);
        }
        return null;
    }

    @Override
    public PageBean<Manager> selectManagerPage(Manager manager) {
        ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria criteria = managerExample.createCriteria();
        PageHelper.startPage(manager.getPageNum(),10);
        List<Manager> managers = managerDao.selectByExample(managerExample);
        return new PageBean<Manager>(managers);
    }

    @Override
    public Manager selectManagerById(Integer managerId) {
        return managerDao.selectByPrimaryKey(managerId);
    }

    @Override
    public int updateManager(Manager manager) {
        return managerDao.updateByPrimaryKeySelective(manager);
    }

    @Override
    public List<Manager> getManagerByName(String managerName) {
        ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria criteria = managerExample.createCriteria();
        criteria.andManagerNameEqualTo(managerName);
        return  managerDao.selectByExample(managerExample);
    }

    @Override
    public int insertManager(Manager manager) {
        return managerDao.insertSelective(manager);
    }

    @Override
    public int deleteById(Integer managerId) {
        return managerDao.deleteByPrimaryKey(managerId);
    }
}
