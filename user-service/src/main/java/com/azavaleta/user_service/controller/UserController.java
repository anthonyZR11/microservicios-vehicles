package com.azavaleta.user_service.controller;

import com.azavaleta.user_service.entity.User;
import com.azavaleta.user_service.model.Car;
import com.azavaleta.user_service.model.Motorbike;
import com.azavaleta.user_service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    public final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        if(users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByid(@PathVariable Integer id) {
        User user = userService.getUserById(id);

        if(user == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User request) {
        System.out.println(request);
        User newUser = userService.createUser(request);

        return ResponseEntity.ok(newUser);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User request, Integer id) {
        User updateUser = userService.updateUser(request, id);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.notFound().build();
    }

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackGetCars")
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCarByUserId(@PathVariable Integer userId) {
        List<Car> carsByUserId = userService.getCarsByUserId(userId);

        if(carsByUserId == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(carsByUserId);
    }

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackCreateCars")
    @PostMapping("/cars/{userId}")
    public ResponseEntity<Car> createCarToUser(@PathVariable Integer userId, @RequestBody Car request) {
        Car newCarToUser = userService.createCarToUser(userId, request);

        return ResponseEntity.ok(newCarToUser);
    }

    @CircuitBreaker(name = "motorbikesCB", fallbackMethod = "fallbackGetMotorbikes")
    @GetMapping("/motorbikes/{userId}")
    public ResponseEntity<List<Motorbike>> getMotorbikesByUserId(@PathVariable Integer userId) {
        List<Motorbike> motorbikeByUserId = userService.getMotorbikesByUserId(userId);

        if(motorbikeByUserId == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(motorbikeByUserId);
    }

    @CircuitBreaker(name = "motorbikesCB", fallbackMethod = "fallbackCreateMotorbikes")
    @PostMapping("/motorbikes/{userId}")
    public ResponseEntity<Motorbike> createMotorbikeToUser(@PathVariable Integer userId, @RequestBody Motorbike request) {
        Motorbike newMotorbikeToUser = userService.createMotorbikeToUser(userId, request);

        return ResponseEntity.ok(newMotorbikeToUser);
    }

    @CircuitBreaker(name = "vehiclesCB", fallbackMethod = "fallbackGetVehicles")
    @GetMapping("/{userId}/all/vehicles")
    public ResponseEntity<Map<String, Object>> getAllUserVehicles(@PathVariable Integer userId) {
        Map<String, Object> getUserVehicles = userService.getAllUserVehicles(userId);

        if(getUserVehicles == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(getUserVehicles);
    }

    private ResponseEntity<List<Car>> fallbackGetCars(@PathVariable Integer userId, RuntimeException runtimeException) {
        return new ResponseEntity("No se pueden cargar los carros para el usuario " + userId + " en este momento", HttpStatus.SERVICE_UNAVAILABLE);
    }

    private ResponseEntity<Car> fallbackCreateCars(@PathVariable Integer userId, @RequestBody Car request, RuntimeException runtimeException) {
        return new ResponseEntity("No se pueden crear carros para el usuario " + userId + " en este momento", HttpStatus.SERVICE_UNAVAILABLE);
    }

    private ResponseEntity<List<Car>> fallbackGetMotorbikes(@PathVariable Integer userId, RuntimeException runtimeException) {
        return new ResponseEntity("No se pueden cargar las motos para el usuario " + userId + " en este momento", HttpStatus.SERVICE_UNAVAILABLE);
    }

    private ResponseEntity<Motorbike> fallbackCreateMotorbikes(@PathVariable Integer userId, @RequestBody Motorbike request, RuntimeException runtimeException) {
        return new ResponseEntity("No se pueden crear motos para el usuario " + userId + " en este momento", HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<Map<String, Object>> fallbackGetVehicles(@PathVariable Integer userId) {
        return new ResponseEntity("No se pueden cargar los vehiculos para el usuario " + userId + " en este momento", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
