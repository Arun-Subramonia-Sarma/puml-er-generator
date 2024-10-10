package com.fk.facilities.dy.er.generator.models;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class TableModel {
    @NonNull
    private String tableName;
    private List<String> columns;
    private List<String> pks;
}
