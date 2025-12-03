package com.azavaleta.user_service.service.feignclients;

import com.azavaleta.user_service.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "car-service",
        url = "http://localhost:8081",
        path = "/api/v1/cars"
)
public interface  CarFeignClient {

    @PostMapping
    Car createCar (@RequestBody Car request);

    @GetMapping("/users/{userId}")
    List<Car> getAllCars (@PathVariable Integer userId);
}
