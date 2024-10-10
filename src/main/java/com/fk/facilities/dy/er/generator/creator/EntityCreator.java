package com.fk.facilities.dy.er.generator.creator;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class EntityCreator {
    @NonNull
    private String tableName;
    private List<String> pkColumns;
    private List<String> columns;

    public String generatoEntity(){
        StringBuilder builder = new StringBuilder("entity " + this.getTableName()+"{\n");
        if(this.getPkColumns() !=null && !this.getPkColumns().isEmpty()){
            builder.append("\t+");
            builder.append(String.join("\n\t+",this.getPkColumns()));
            this.getColumns().removeAll(this.getPkColumns());
        }
        builder.append("\n");
        builder.append("--\n");

        builder.append("\t");
        builder.append(String.join("\n\t",columns));
        builder.append("\n}\n");
        return builder.toString();
    }

}
