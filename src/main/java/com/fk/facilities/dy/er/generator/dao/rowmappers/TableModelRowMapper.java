package com.fk.facilities.dy.er.generator.dao.rowmappers;

import com.fk.facilities.dy.er.generator.models.TableModel;
import lombok.Getter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Getter
public class TableModelRowMapper implements RowMapper<TableModel> {
    Map<String, List<String>> tables = new LinkedHashMap<>();


    @Override
    public TableModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        String currentTableName = rs.getString("table_name");
        List<String> columns = tables.get(currentTableName);
        if(columns == null || columns.isEmpty()) {
            columns =new ArrayList<>();
            tables.put(currentTableName, columns);
        }
        columns.add(rs.getString("column_name"));

        return null;
    }

}
