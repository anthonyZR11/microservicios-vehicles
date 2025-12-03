package com.azavaleta.user_service.service;

import com.azavaleta.user_service.entity.User;
import com.azavaleta.user_service.model.Car;
import com.azavaleta.user_service.model.Motorbike;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User>getAllUsers();
    User getUserById(Integer id);
    User createUser(User user);
    User updateUser(User user, Integer id);
    void deleteUser(Integer id);
    List<Car> getCarsByUserId(Integer userId);
    List<Motorbike> getMotorbikesByUserId(Integer userId);
    Car createCarToUser(Integer userId, Car request);
    Motorbike createMotorbikeToUser(Integer userId, Motorbike request);
    Map<String, Object> getAllUserVehicles(Integer userId);
}
