package com.fk.facilities.dy.er.generator.dao;


import com.fk.facilities.dy.er.generator.dao.rowmappers.TableModelRowMapper;
import com.fk.facilities.dy.er.generator.dao.rowmappers.TableRelationShipRowMapper;
import com.fk.facilities.dy.er.generator.models.TableRelationship;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseDAO {
    @NonNull
    JdbcTemplate jdbcTemplate;

    public int getTableCount(){
        String query = """
                
                    
                """;
        log.info("Executing the query: {}", query);
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public List<String> getPublicTableNames() {
        log.info("Executing the query: {}", QueryConstants.FETCH_ALL_TABLE_NAMES_QUERY);
        return jdbcTemplate.queryForList(QueryConstants.FETCH_ALL_TABLE_NAMES_QUERY, String.class);
    }

    public List<String> getPrimaryKeyColumns(String tableName, String schemaName) {
        log.info("Executing the query: {} with params tableName={} and schemaName={}", QueryConstants.FETCH_PK_COLUMNS_QUERY,tableName,schemaName);
        return jdbcTemplate.queryForList(QueryConstants.FETCH_PK_COLUMNS_QUERY, String.class, tableName, schemaName);
    }

    public List<String> getColumns(String tableName, String schemaName) {
        log.info("Executing the query: {} with params tableName={} and schemaName={}", QueryConstants.FETCH_COLUMNS_QUERY,tableName,schemaName);
        return jdbcTemplate.queryForList(QueryConstants.FETCH_COLUMNS_QUERY, String.class, tableName, schemaName);
    }

    public Map<String, List<String>> getTableColumns(){
        TableModelRowMapper rowMapper=new TableModelRowMapper();
        jdbcTemplate.query(QueryConstants.FETCH_ALL_TABLE_COLUMNS_QUERY, rowMapper);
        return rowMapper.getTables();
    }

    public Map<String, List<String>> getTableKeyColumns(){
        TableModelRowMapper rowMapper=new TableModelRowMapper();
        jdbcTemplate.query(QueryConstants.FETCH_ALL_TABLE_PK_COLUMNS_QUERY, rowMapper);
        return rowMapper.getTables();
    }

    public List<TableRelationship> getAllTableRelationships() {
        return jdbcTemplate.query(QueryConstants.FETCH_ALL_TABLE_RELATIONSHIP, new TableRelationShipRowMapper());
    }

}
