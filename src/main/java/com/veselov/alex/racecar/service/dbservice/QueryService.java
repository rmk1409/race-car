package com.veselov.alex.racecar.service.dbservice;

import com.veselov.alex.racecar.data.dao.QueryRepository;
import com.veselov.alex.racecar.data.entity.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@Service
public class QueryService {
    @Autowired
    private QueryRepository repository;

    public List<Query> findAll() {
        List<Query> result = this.repository.findAll();
        result.forEach(
                i -> {
                    try {
                        i.setHref(URLEncoder.encode(i.getHref(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        log.error("There is an exception -> {}, {}", e, e.getMessage());
                    }
                }
        );
        return result;
    }

    public void save(Query query) {
        this.repository.save(query);
    }

    public void deleteById(int id) {
        this.repository.deleteById(id);
    }
}
