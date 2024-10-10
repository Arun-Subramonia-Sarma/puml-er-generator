package com.fk.facilities.dy.er.generator.dao.rowmappers;

import com.fk.facilities.dy.er.generator.models.TableRelationship;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableRelationShipRowMapper implements RowMapper<TableRelationship> {
    @Override
    public TableRelationship mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TableRelationship(rs.getString("parent_table"), rs.getString("child_table"), rs.getString("constraint_name"));
    }
}
