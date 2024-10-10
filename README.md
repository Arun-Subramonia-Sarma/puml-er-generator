# puml-er-generator
Entity Diagram generator from the given datasource in the PUML IE format

### QUERY to fetch the relationship along with the columns match
```sql
SELECT
    con.conname AS constraint_name,
    parent.relname AS parent_table,
    parent_cols.attname AS child_column,
    child.relname AS child_table,
    child_cols.attname AS child_column
FROM
    pg_constraint con
        JOIN pg_class child ON con.conrelid = child.oid
        JOIN pg_class parent ON con.confrelid = parent.oid
        JOIN unnest(con.conkey) WITH ORDINALITY AS child_cols_num (child_col_num, child_col_idx) ON true
        JOIN unnest(con.confkey) WITH ORDINALITY AS parent_cols_num (parent_col_num, parent_col_idx)
             ON child_cols_num.child_col_idx = parent_cols_num.parent_col_idx
        JOIN pg_attribute child_cols ON child_cols.attnum = child_cols_num.child_col_num
        AND child_cols.attrelid = child.oid
        JOIN pg_attribute parent_cols ON parent_cols.attnum = parent_cols_num.parent_col_num
        AND parent_cols.attrelid = parent.oid
        JOIN pg_namespace table_schema on con.connamespace = table_schema.oid
WHERE
    con.contype = 'f'
  AND parent.relname not like '%partition%'
  AND parent.relname not like '%default'
  AND child.relname not like '%partition%'
  AND child.relname not like '%default'
  AND table_schema.nspname='public'
order by
    parent.relname,
    con.conname;
```
