package com.veselov.alex.racecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RaceCarApplication {
    public static void main(String[] args) {
        SpringApplication.run(RaceCarApplication.class, args);
    }
}
