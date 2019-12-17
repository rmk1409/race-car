package com.veselov.alex.racecar.data.dao;

import com.veselov.alex.racecar.data.entity.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<Query, Integer> {
}
