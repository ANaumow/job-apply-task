package org.company.controller;

import lombok.RequiredArgsConstructor;
import org.company.domain.Region;
import org.company.service.RegionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/dict/regions")
    public List<Region> getAll() {
        return regionService.getAll();
    }

    @GetMapping("/dict/regions/{id}")
    public Region getById(@PathVariable Long id) {
        return regionService.getById(id);
    }

    @PostMapping("/dict/regions")
    public Long addRegion(@RequestBody Region region) {
        return regionService.add(region);
    }

    @DeleteMapping("/dict/regions/{id}")
    public void removeRegion(@PathVariable Long id) {
        regionService.remove(id);
    }

    @PatchMapping("/dict/regions/{id}")
    public void modify(@PathVariable Long id, @RequestBody Region region) {
        regionService.modify(id, region);
    }
}
