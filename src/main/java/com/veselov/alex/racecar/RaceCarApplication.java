package com.veselov.alex.racecar;

import com.veselov.alex.racecar.data.dao.QueryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RaceCarApplication implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(RaceCarApplication.class);
	@Autowired
	private QueryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(RaceCarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Show all queries -> {}", this.repository.findAll());
	}
}
