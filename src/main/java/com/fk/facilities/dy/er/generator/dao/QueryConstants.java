package com.fk.facilities.dy.er.generator.dao;

public interface QueryConstants {
    public final static String FETCH_ALL_TABLE_NAMES_QUERY = """
            SELECT
                tablename
            FROM
                pg_tables
            WHERE
                schemaname='public'
                and tablename not like '%partition%'
                and tablename not like '%default'
            ORDER BY
                tablename
            """;

    public final static String FETCH_PK_COLUMNS_QUERY = """
            SELECT
                kcu.column_name
            FROM
                information_schema.table_constraints tc
                JOIN information_schema.key_column_usage kcu\s
                  ON tc.constraint_name = kcu.constraint_name
                  AND tc.table_schema = kcu.table_schema
            WHERE
                tc.constraint_type = 'PRIMARY KEY'
                AND tc.table_name = ?
                AND tc.table_schema = ?;
            """;

    public final static String FETCH_COLUMNS_QUERY = """
            SELECT
                table_name,
                column_name
            FROM
                information_schema.columns
            where
                table_schema='public'
                and table_name not like '%partition%'
                and table_name not like '%default'
            ORDER BY
                table_name,
                ordinal_position
                ;
            """;
    public final static String FETCH_ALL_TABLE_COLUMNS_QUERY = """
            SELECT
                table_name,
                column_name
            FROM
                information_schema.columns
            where
                table_schema='public'
                and table_name not like '%partition%'
                and table_name not like '%default'
                and table_name not like 'databasechange%'
                and table_name not like 'pg%'
            ORDER BY
                table_name,
                ordinal_position
            """;
    public final static String FETCH_ALL_TABLE_PK_COLUMNS_QUERY = """
            SELECT
                tc.table_name table_name,
                kcu.column_name column_name
            FROM
                information_schema.table_constraints tc
                    JOIN information_schema.key_column_usage kcu
                         ON tc.constraint_name = kcu.constraint_name
                             AND tc.table_schema = kcu.table_schema
            WHERE
                tc.constraint_type = 'PRIMARY KEY'
              and tc.table_name not like '%partition%'
              and tc.table_name not like '%default'
              AND kcu.table_schema = 'public'
            order by tc.table_name;
            """;

    public final static String FETCH_ALL_TABLE_RELATIONSHIP = """
            
            SELECT
                distinct
                con.conname AS constraint_name,
                parent.relname AS parent_table,
                child.relname AS child_table
            FROM
                pg_constraint con
                    JOIN pg_class child ON con.conrelid = child.oid
                    JOIN pg_class parent ON con.confrelid = parent.oid
                    JOIN pg_namespace table_schema on con.connamespace = table_schema.oid
            WHERE
                con.contype = 'f'
              AND parent.relname not like '%partition%'
              AND parent.relname not like '%default'
              AND child.relname not like '%partition%'
              AND child.relname not like '%default'
              AND table_schema.nspname='public'
            ORDER BY
                parent.relname 
            """;
}
