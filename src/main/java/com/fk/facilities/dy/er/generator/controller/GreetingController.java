package com.fk.facilities.dy.er.generator.controller;

import com.fk.facilities.dy.er.generator.dao.DatabaseDAO;
import com.fk.facilities.dy.er.generator.service.DatabaseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/database")
@RequiredArgsConstructor
@Slf4j
public class GreetingController {

    @NonNull
    DatabaseService databaseService;

    @GetMapping("/table")
    public List<String> getPublicTableNames(){
        return databaseService.getPublicTableNames();
    }
    @GetMapping("/primarykey")
    public List<String> getPrimaryKeyColumnsForTable(@RequestParam("tableName") String tableName, @RequestParam("schemaName") String schemaName){
        return databaseService.getPrimaryKeyColumnsForTable(tableName, schemaName);
    }

    @GetMapping("/columns")
    public List<String> getColumns(@RequestParam("tableName") String tableName, @RequestParam("schemaName") String schemaName){
        return databaseService.getColumns(tableName, schemaName);
    }

    @GetMapping("/puml")
    public List<String> getPUMLEntities() throws IOException {
        return databaseService.getPUMLEntities();
    }
    @GetMapping("/puml/er")
    public String getERPUMLEntities() throws IOException {
        return databaseService.getERPUMLEntities();
    }

    @GetMapping("/tables/columns")
    public Map<String, List<String>> getTableColumns(){
        return databaseService.getTableColumns();
    }

    @GetMapping("/tables/keys/columns")
    public Map<String, List<String>> getTableKeyColumns(){
        return databaseService.getTableKeyColumns();
    }

}
