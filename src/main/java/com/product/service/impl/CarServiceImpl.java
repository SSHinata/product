package com.product.service.impl;

import com.product.dao.CarDao;
import com.product.model.Car;
import com.product.model.CarExample;
import com.product.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carService")
public class CarServiceImpl implements ICarService {
    @Autowired
    private CarDao carDao;

    @Override
    public Integer add(Integer userId, Integer productId) {
        //查询该用户下的购物车中是否有此商品
        CarExample example=new CarExample();
        CarExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andProductIdEqualTo(productId);
        List<Car> carList = carDao.selectByExample(example);
        //有则修改,无则添加
        if(carList != null && carList.size() > 0){
            Car car = carList.get(0);
            car.setNumber(car.getNumber() + 1);
            return carDao.updateByPrimaryKeySelective(car);
        }else {
            Car car=new Car();
            car.setUserId(userId);
            car.setProductId(productId);
            car.setNumber(1);
            return carDao.insert(car);
        }
    }

    @Override
    public Car getCarById(Integer carId) {
        Car car = carDao.selectByPrimaryKey(carId);
        return car;
    }

    @Override
    public Integer update(Integer carId,Integer number) {
        Car car=new Car();
        car.setCarId(carId);
        car.setNumber(number);
        int i = carDao.updateByPrimaryKeySelective(car);
        return i;
    }

    @Override
    public Integer deleteOne(Integer carId) {

        return carDao.deleteByPrimaryKey(carId);
    }

    @Override
    public void deleteUserAll(Integer userId) {
        CarExample example=new CarExample();
        CarExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        carDao.deleteByExample(example);
    }

    @Override
    public List<Car> getUserCar(Integer userId) {
        List<Car> cars = carDao.selectCarByUserId(userId);
        if(cars != null && cars.size()>0){
            return cars;
        }
        return null;
    }


}
