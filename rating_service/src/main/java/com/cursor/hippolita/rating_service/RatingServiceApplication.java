package com.cursor.hippolita.rating_service;

import com.cursor.hippolita.rating_service.entity.Rate;
import com.cursor.hippolita.rating_service.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableEurekaClient
@RequiredArgsConstructor
public class RatingServiceApplication {

    private final RateRepository rateRepository;

    public static void main(String[] args) {
        SpringApplication.run(RatingServiceApplication.class, args);
    }

}
