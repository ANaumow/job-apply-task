package org.company.service;

import lombok.RequiredArgsConstructor;
import org.company.data.RegionMapper;
import org.company.domain.Region;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionMapper regionMapper;

    @Override
    @Cacheable(value = "regions", key = "'list'")
    public List<Region> getAll() {
        return regionMapper.selectMany();
    }

    @Override
    @Cacheable(value = "region", key = "#id")
    public Region getById(Long id) {
        return regionMapper.selectById(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "regions", key = "'list'")
    })
    public Long add(Region region) {
        regionMapper.insert(region);
        return region.getRegionId();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "region", key = "#id"),
            @CacheEvict(value = "regions", key = "'list'")
    })
    public void remove(Long id) {
        regionMapper.delete(id);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "region", key = "#id"),
            @CacheEvict(value = "regions", key = "'list'")
    })
    public void modify(Long id, Region region) {
        regionMapper.update(id, region);
    }
}
