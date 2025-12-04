package com.azavaleta.user_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced //Esto se usa para que la api pueda leer la urta del servicio y no la ip
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
