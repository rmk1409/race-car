package com.veselov.alex.racecar.service.dbservice;

import com.veselov.alex.racecar.data.entity.SourceSite;

import java.util.Optional;

public interface SourceSiteService {
    Optional<SourceSite> findById(int id);
}
