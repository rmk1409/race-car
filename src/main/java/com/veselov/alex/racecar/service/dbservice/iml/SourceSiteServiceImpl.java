package com.veselov.alex.racecar.service.dbservice.iml;

import com.veselov.alex.racecar.data.dao.SourceSiteRepository;
import com.veselov.alex.racecar.data.entity.SourceSite;
import com.veselov.alex.racecar.service.dbservice.SourceSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SourceSiteServiceImpl implements SourceSiteService {
    @Autowired
    private SourceSiteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Optional<SourceSite> findById(int id) {
        return this.repository.findById(id);
    }
}
