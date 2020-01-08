package com.veselov.alex.racecar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication
public class RaceCarApplication {
    public static void main(String[] args) {
        SpringApplication.run(RaceCarApplication.class, args);
    }
}
