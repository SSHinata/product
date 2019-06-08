package com.product.service;

import com.product.model.Car;

import java.util.List;

public interface ICarService {

    public Integer update(Integer carId, Integer number);
    public Integer deleteOne(Integer carId);
    public void deleteUserAll(Integer userId);
    public List<Car> getUserCar(Integer userId);

    Integer add(Integer userId, Integer productId);

    Car getCarById(Integer carId);
}
