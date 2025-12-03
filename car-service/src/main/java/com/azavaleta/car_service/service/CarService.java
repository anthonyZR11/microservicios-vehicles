package com.azavaleta.car_service.service;

import com.azavaleta.car_service.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car getCarById(Integer id);
    List<Car> getCarByUserId(Integer userId);
    Car createCar(Car user);
    Car updateCar(Car user, Integer id);
    void deleteCar(Integer id);
}
