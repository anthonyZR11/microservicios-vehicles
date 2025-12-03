package com.azavaleta.motorbike_service.controller;

import com.azavaleta.motorbike_service.entity.Motorbike;
import com.azavaleta.motorbike_service.service.MotorbikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/motorbikes")
public class MotorbikeController {
    public final MotorbikeService motorbikeService;

    @GetMapping
    public ResponseEntity<List<Motorbike>> getAllMotorbikes() {
        List<Motorbike> users = motorbikeService.getAllMotorbikes();

        if(users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorbike> getMotorbikeByid(@PathVariable Integer id) {
        Motorbike motorbike = motorbikeService.getMotorbikeById(id);

        if(motorbike == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(motorbike);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Motorbike>> getMotorbikeByUserId(@PathVariable Integer userId) {
        List<Motorbike> motorbikesByUserId = motorbikeService.getMotorbikeByUserId(userId);

        if(motorbikesByUserId.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(motorbikesByUserId);
    }

    @PostMapping
    public ResponseEntity<Motorbike> createMotorbike(@RequestBody Motorbike request) {
        System.out.println(request);
        Motorbike newMotorbike = motorbikeService.createMotorbike(request);

        return ResponseEntity.ok(newMotorbike);
    }

    @PutMapping
    public ResponseEntity<Motorbike> updateMotorbike(@RequestBody Motorbike request, Integer id) {
        Motorbike updateMotorbike = motorbikeService.updateMotorbike(request, id);
        return ResponseEntity.ok(updateMotorbike);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Motorbike> deleteMotorbike(@PathVariable Integer id) {
        motorbikeService.deleteMotorbike(id);
        return ResponseEntity.notFound().build();
    }
}
