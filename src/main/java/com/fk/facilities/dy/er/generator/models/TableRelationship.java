package com.fk.facilities.dy.er.generator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TableRelationship {
    private String parentTable;
    private String childTable;
    private String constraintName;
}
