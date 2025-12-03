package com.azavaleta.motorbike_service.service;

import com.azavaleta.motorbike_service.entity.Motorbike;

import java.util.List;

public interface MotorbikeService {
    List<Motorbike> getAllMotorbikes();
    Motorbike getMotorbikeById(Integer id);
    List<Motorbike> getMotorbikeByUserId(Integer userId);
    Motorbike createMotorbike(Motorbike user);
    Motorbike updateMotorbike(Motorbike user, Integer id);
    void deleteMotorbike(Integer id);
}
