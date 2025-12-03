package com.azavaleta.user_service.service.feignclients;

import com.azavaleta.user_service.model.Car;
import com.azavaleta.user_service.model.Motorbike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "Motorbike-service",
        url = "http://localhost:8082",
        path = "/api/v1/motorbikes"
)
public interface MotorbikeFeignClient {
    @PostMapping
    Motorbike createMotorbike(@RequestBody Motorbike request);

    @GetMapping("/users/{userId}")
    List<Motorbike> getAllMotorbikes (@PathVariable Integer userId);
}
