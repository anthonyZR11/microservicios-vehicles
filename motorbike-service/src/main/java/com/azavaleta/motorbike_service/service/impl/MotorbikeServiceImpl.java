package com.azavaleta.motorbike_service.service.impl;

import com.azavaleta.motorbike_service.entity.Motorbike;
import com.azavaleta.motorbike_service.repository.MotorbikeRepository;
import com.azavaleta.motorbike_service.service.MotorbikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MotorbikeServiceImpl implements MotorbikeService {
    public final MotorbikeRepository motorbikeRepository;

    public List<Motorbike> getAllMotorbikes() {
        return motorbikeRepository.findAll();
    }

    @Override
    public Motorbike getMotorbikeById(Integer id) {
        return motorbikeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Motorbike> getMotorbikeByUserId(Integer userId) {
        return motorbikeRepository.findMotorbikeByUserId(userId);
    }

    @Override
    public Motorbike createMotorbike(Motorbike user) {
        return motorbikeRepository.save(user);
    }

    @Override
    public Motorbike updateMotorbike(Motorbike user, Integer id) {
        var updateMotorbike = motorbikeRepository.findById(id).orElseThrow(() -> new RuntimeException("La moto no existe"));
        return motorbikeRepository.save(updateMotorbike);
    }

    @Override
    public void deleteMotorbike(Integer id) {
        motorbikeRepository.deleteById(id);
    }
}
