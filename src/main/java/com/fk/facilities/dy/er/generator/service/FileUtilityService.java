package com.fk.facilities.dy.er.generator.service;

import com.fk.facilities.dy.er.generator.models.TableModel;
import com.fk.facilities.dy.er.generator.models.TableRelationship;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;

import javax.swing.table.TableColumnModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUtilityService {

    @NonNull
    private final Template template;

    @SneakyThrows
    public void write2File(List<String> content, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("@startuml\n");
        content.forEach(entityContent -> {
            try {
                log.info("Writing into the file {} the content {} ",fileName, entityContent);
                writer.write(entityContent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        writer.write("@enduml\n");
        writer.flush();
        writer.close();
    }
    public void write2File(String content, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);
        writer.flush();
        writer.close();
    }

    public String getEntityContent(List<TableModel> entities, List<TableRelationship> relationships) {
        VelocityContext context = new VelocityContext();
        context.put("entities", entities);
        context.put("relationships", relationships);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

}
