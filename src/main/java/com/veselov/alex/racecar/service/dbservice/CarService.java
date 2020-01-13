package com.veselov.alex.racecar.service.dbservice;

import com.veselov.alex.racecar.data.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    long count();

    Page<Car> findAll(Pageable pageable);

    List<Car> saveAll(Iterable<Car> entities);
}
