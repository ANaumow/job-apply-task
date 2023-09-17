package org.company.controller;

import org.company.domain.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.company.test.Utils.jsonFrom;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(statements = "truncate table region; ALTER TABLE region ALTER COLUMN region_id RESTART WITH 1;")
public class RegionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JdbcTemplate jdbcTemplate;

    Region region;

    @BeforeEach
    void beforeEach() throws Exception {
        region = new Region();
        region.setName("len");
        region.setShortName("ln");

        this.mockMvc.perform(post("/dict/regions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonFrom(region)));

        region.setRegionId(1L);
    }

    @Test
    void When_RegionIsAdded_Expect_CorrectRetrieving() throws Exception {
        this.mockMvc.perform(get("/dict/regions"))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonFrom(List.of(region))));

        this.mockMvc.perform(get("/dict/regions/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonFrom(region)));
    }

    @Test
    void When_PatchField_Expect_FieldToBeChanged() throws Exception {
        Region namePatchRegion = new Region();
        namePatchRegion.setName("leningrad region");

        this.mockMvc.perform(patch("/dict/regions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFrom(namePatchRegion)))
                .andExpect(status().isOk());

        region.setName("leningrad region");

        this.mockMvc.perform(get("/dict/regions"))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonFrom(List.of(region))));
    }

    @Test
    void When_DeleteSingleRegion_Expect_NoMoreRegions() throws Exception {

        this.mockMvc.perform(delete("/dict/regions/1"))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/dict/regions"))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonFrom(emptyList())));
    }

}
