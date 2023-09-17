package org.company.service;

import org.company.config.CachingConfig;
import org.company.data.RegionMapper;
import org.company.domain.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({CachingConfig.class, RegionServiceImpl.class})
class RegionServiceImplTest {

    @Autowired
    RegionService regionService;

    @MockBean
    RegionMapper regionMapper;

    @Test
    void cacheScenarioTest() {
        int selectByIdExpectCount = 0;
        int selectManyExpectCount = 0;

        regionService.getById(1L);
        regionService.getById(1L);
        selectByIdExpectCount++;

        verify(regionMapper, times(selectByIdExpectCount)).selectById(any());

        regionService.modify(1L, null);

        regionService.getById(1L);
        regionService.getById(1L);
        selectByIdExpectCount++;

        verify(regionMapper, times(selectByIdExpectCount)).selectById(any());

        regionService.getAll();
        regionService.getAll();
        selectManyExpectCount++;

        verify(regionMapper, times(selectManyExpectCount)).selectMany();

        regionService.add(new Region(null, "reg", "rg"));

        regionService.getAll();
        regionService.getAll();
        selectManyExpectCount++;

        verify(regionMapper, times(selectManyExpectCount)).selectMany();

        regionService.getById(1L);
        regionService.getById(1L);

        verify(regionMapper, times(selectByIdExpectCount)).selectById(any());

        regionService.remove(1L);

        regionService.getAll();
        regionService.getAll();
        selectManyExpectCount++;

        verify(regionMapper, times(selectManyExpectCount)).selectMany();

        regionService.getById(1L);
        regionService.getById(1L);
        selectByIdExpectCount++;

        verify(regionMapper, times(selectByIdExpectCount)).selectById(any());
    }

}
