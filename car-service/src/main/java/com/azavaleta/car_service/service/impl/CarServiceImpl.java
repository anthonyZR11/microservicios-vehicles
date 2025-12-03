package com.azavaleta.car_service.service.impl;

import com.azavaleta.car_service.entity.Car;
import com.azavaleta.car_service.repository.CarRepository;
import com.azavaleta.car_service.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    public final CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Integer id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public List<Car> getCarByUserId(Integer userId) {
        return carRepository.findCarByUserId(userId);
    }

    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car, Integer id) {
        var updateUser = carRepository.findById(id).orElseThrow(() -> new RuntimeException("El carro no existe"));
        return carRepository.save(updateUser);
    }

    @Override
    public void deleteCar(Integer id) {
        carRepository.deleteById(id);
    }
}
