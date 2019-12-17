package com.veselov.alex.racecar.data.dao;

import com.veselov.alex.racecar.data.entity.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Auto, Integer> {
}
