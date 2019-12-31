package com.veselov.alex.racecar.data.dao;

import com.veselov.alex.racecar.data.entity.SourceSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceSiteRepository extends JpaRepository<SourceSite, Integer> {
}
