package org.company.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Region extends Dictionary {

    // идентификатор
    private Long regionId;

    // наименование
    private String name;

    // сокращённое наименование
    private String shortName;

}
