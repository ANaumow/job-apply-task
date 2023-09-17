package org.company.data;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;
import org.company.domain.Region;

public class RegionSqlProvider implements ProviderMethodResolver {

    public static String update(Long id, Region region) {
        return new SQL() {{
            UPDATE("region");
            if (region.getName() != null) {
                SET("name = '%s'".formatted(region.getName()));
            }
            if (region.getShortName() != null) {
                SET("short_name = '%s'".formatted(region.getShortName()));
            }
            WHERE("region_id = " + id);
        }}.toString();
    }

}
