package org.company.service;

import org.company.domain.Region;

import java.util.List;

public interface RegionService {
    List<Region> getAll();

    Long add(Region region);

    void remove(Long id);

    void modify(Long id, Region region);

    Region getById(Long id);
}
