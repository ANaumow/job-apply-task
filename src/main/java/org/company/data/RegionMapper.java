package org.company.data;

import org.apache.ibatis.annotations.*;
import org.company.domain.Region;

import java.util.List;

@Mapper
public interface RegionMapper {

    @Select("select * from region")
    List<Region> selectMany();

    @Select("select * from region where region_id = #{id}")
    Region selectById(Long id);

    @Insert("insert into region(name, short_name) values(#{name}, #{shortName})")
    @Options(useGeneratedKeys = true, keyProperty = "regionId")
    void insert(Region region);

    @Delete("delete from region where region_id = #{regionId}")
    void delete(Long regionId);

    @UpdateProvider(RegionSqlProvider.class)
    void update(Long id, Region region);

}
