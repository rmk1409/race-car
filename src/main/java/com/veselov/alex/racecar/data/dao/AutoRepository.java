package com.veselov.alex.racecar.data.dao;

import com.veselov.alex.racecar.data.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Car, Integer> {
}
