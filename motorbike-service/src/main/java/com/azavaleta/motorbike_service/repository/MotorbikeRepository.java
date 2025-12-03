package com.azavaleta.motorbike_service.repository;

import com.azavaleta.motorbike_service.entity.Motorbike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorbikeRepository extends JpaRepository<Motorbike, Integer> {
    List<Motorbike> findMotorbikeByUserId(Integer userId);
}
