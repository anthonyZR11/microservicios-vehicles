package com.azavaleta.car_service.controller;

import com.azavaleta.car_service.entity.Car;
import com.azavaleta.car_service.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {
    public final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();

        if(cars.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarByid(@PathVariable Integer id) {
        Car car = carService.getCarById(id);

        if(car == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(car);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Car>> getCarByUserId(@PathVariable Integer userId) {
        List<Car> car = carService.getCarByUserId(userId);

        if(car == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car request) {
        System.out.println(request);
        Car newCar = carService.createCar(request);

        return ResponseEntity.ok(newCar);
    }

    @PutMapping
    public ResponseEntity<Car> updateCar(@RequestBody Car request, Integer id) {
        Car updateCar = carService.updateCar(request, id);
        return ResponseEntity.ok(updateCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Integer id) {
        carService.deleteCar(id);
        return ResponseEntity.notFound().build();
    }
}
