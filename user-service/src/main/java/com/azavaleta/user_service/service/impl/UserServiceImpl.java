package com.azavaleta.user_service.service.impl;

import com.azavaleta.user_service.entity.User;
import com.azavaleta.user_service.model.Car;
import com.azavaleta.user_service.model.Motorbike;
import com.azavaleta.user_service.repository.UserRepository;
import com.azavaleta.user_service.service.UserService;
import com.azavaleta.user_service.service.feignclients.CarFeignClient;
import com.azavaleta.user_service.service.feignclients.MotorbikeFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public final UserRepository userRepository;
    public final RestTemplate restTemplate;
    public final CarFeignClient carFeignClient;
    public final MotorbikeFeignClient motorbikeFeignClient;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<Car> getCarsByUserId(Integer userId) {
        List<Car> cars = restTemplate.exchange("http://car-service/api/v1/cars/users/" + userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Car>>() {
                }).getBody();

        return cars != null ? cars : Collections.emptyList();
    }

    public List<Motorbike> getMotorbikesByUserId(Integer userId) {
        List<Motorbike> motorbikes = restTemplate.exchange("http://motorbike-service/api/v1/motorbikes/users/" + userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Motorbike>>() {
                }).getBody();

        return motorbikes != null ? motorbikes : Collections.emptyList();
    }

    @Override
    public Car createCarToUser(Integer userId, Car car) {
        car.setUserId(userId);
        return carFeignClient.createCar(car);
    }

    @Override
    public Motorbike createMotorbikeToUser(Integer userId, Motorbike motorbike) {
        motorbike.setUserId(userId);
        return motorbikeFeignClient.createMotorbike(motorbike);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, Integer id) {
        var updateUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("El usuario no existe"));
        return userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Map<String, Object> getAllUserVehicles(Integer userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);

        if(user == null) {
            result.put("message", "El usuario no existe");
        }
        result.put("user", user);

        List<Car> getCarsToUser = carFeignClient.getAllCars(userId);
        List<Motorbike> getMotorbikeToUser = motorbikeFeignClient.getAllMotorbikes(userId);

        if(getCarsToUser.isEmpty()) {
            result.put("cars", "El usario no tiene carros");
        }else {
            result.put("cars", getCarsToUser);
        }

        if(getMotorbikeToUser.isEmpty()) {
            result.put("motorbikes", "El usario no tiene motos");
        }else {
            result.put("motorbikes", getMotorbikeToUser);
        }

        return result;
    }
}
