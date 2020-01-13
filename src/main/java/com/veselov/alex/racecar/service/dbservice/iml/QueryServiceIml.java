package com.veselov.alex.racecar.service.dbservice.iml;

import com.veselov.alex.racecar.data.dao.QueryRepository;
import com.veselov.alex.racecar.data.entity.Query;
import com.veselov.alex.racecar.service.dbservice.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@Service
public class QueryServiceIml implements QueryService {
    @Autowired
    private QueryRepository repository;

    @Transactional(readOnly = true)
    public List<Query> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Query> findAllEncoded() {
        List<Query> result = this.findAll();
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

    @Override
    @Transactional
    public void save(Query query) {
        this.repository.save(query);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        this.repository.deleteById(id);
    }
}
