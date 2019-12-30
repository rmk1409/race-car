package com.veselov.alex.racecar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class RaceCarApplication {
    public static void main(String[] args) {
        SpringApplication.run(RaceCarApplication.class, args);

        new RaceCarApplication().runTelegram();
    }

    private void runTelegram() {
        ApiContextInitializer.init();
    }
}
