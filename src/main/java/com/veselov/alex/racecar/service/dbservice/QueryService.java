package com.veselov.alex.racecar.service.dbservice;

import com.veselov.alex.racecar.data.entity.Query;

import java.util.List;

public interface QueryService {
    List<Query> findAll();

    List<Query> findAllEncoded();

    void save(Query query);

    void deleteById(int id);
}
