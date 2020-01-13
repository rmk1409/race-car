package com.veselov.alex.racecar.service.dbservice.iml;

import com.veselov.alex.racecar.data.dao.CarRepository;
import com.veselov.alex.racecar.data.entity.Car;
import com.veselov.alex.racecar.service.dbservice.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository repository;

    @Override
    public long count() {
        return this.repository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Car> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @Override
    public List<Car> saveAll(Iterable<Car> entities) {
        return this.repository.saveAll(entities);
    }
}
