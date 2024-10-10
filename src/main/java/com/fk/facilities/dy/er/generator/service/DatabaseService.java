package com.fk.facilities.dy.er.generator.service;

import com.fk.facilities.dy.er.generator.creator.EntityCreator;
import com.fk.facilities.dy.er.generator.dao.DatabaseDAO;
import com.fk.facilities.dy.er.generator.models.TableModel;
import com.fk.facilities.dy.er.generator.models.TableRelationship;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DatabaseService {

    @NonNull
    private final DatabaseDAO databaseDAO;

    @NonNull
    private final FileUtilityService fileUtilityService;


    public List<String> getPublicTableNames(){
        return databaseDAO.getPublicTableNames();
    }

    public List<String> getPrimaryKeyColumnsForTable(String tableName, String schemaName){
        return databaseDAO.getPrimaryKeyColumns(tableName, schemaName);
    }
    public List<String> getColumns(String tableName, String schemaName){
        return databaseDAO.getColumns(tableName, schemaName);
    }

    public Map<String, List<String>> getTableColumns(){
        return databaseDAO.getTableColumns();
    }

    public Map<String, List<String>> getTableKeyColumns(){
        return databaseDAO.getTableKeyColumns();
    }

    public String getERPUMLEntities() throws IOException {
        Map<String, List<String>> tableColumns = getTableColumns();
        Map<String, List<String>> tableKeyColumns = getTableKeyColumns();
        List<TableModel> entities = tableColumns.entrySet().stream()
                .map(entry -> {
                        String tableName = entry.getKey();
                        List<String>  keyColumns = tableKeyColumns.get(tableName);
                        List<String> columns = tableColumns.get(tableName);
                        if(keyColumns!=null && !keyColumns.isEmpty())
                            columns.removeAll(keyColumns);
                        return  new TableModel(tableName, columns, keyColumns);
                    }
                )
                .collect(Collectors.toList());
        List<TableRelationship> relationships=databaseDAO.getAllTableRelationships();
        String content = fileUtilityService.getEntityContent(entities,relationships);
//        List<String> contents= tableColumns.entrySet().stream()
//                .map(entry -> {
//                    String table = entry.getKey();
//                    EntityCreator entityCreator = new EntityCreator(table, tableKeyColumns.get(table), entry.getValue());
//                    return entityCreator.generatoEntity();
//
//                })
//                .collect(Collectors.toList());
        fileUtilityService.write2File(content,"dyf.entity.puml");
        return content;
    }
    public List<String> getPUMLEntities() throws IOException {
        List<String> contents= getPublicTableNames().stream()
                .map(table->{
                    StringBuilder builder = new StringBuilder("entity " + table+"{\n");
                    List<String> primaryKeyColumns = getPrimaryKeyColumnsForTable(table,"public");
                    if(!primaryKeyColumns.isEmpty()){
                        builder.append("\t+");
                        builder.append(String.join("\n\t+",primaryKeyColumns));
                    }
                    builder.append("\n");
                    builder.append("--\n");
                    List<String> columns = getColumns(table,"public");
                    columns.removeAll(primaryKeyColumns);
                    builder.append("\t");
                    builder.append(String.join("\n\t",columns));
                    builder.append("\n}\n");
                    log.info(builder.toString());
                    return builder.toString();
                })
                .collect(Collectors.toList());
        fileUtilityService.write2File(contents,"entity.puml");
        return contents;
    }


}
