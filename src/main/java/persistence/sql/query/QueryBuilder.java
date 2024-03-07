package persistence.sql.query;

import persistence.sql.dto.db.Column;
import persistence.sql.dto.db.Table;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QueryBuilder {

    public String createTableQuery(Table table, List<Column> columns) {
        StringBuilder sql = new StringBuilder();
        sql.append(String.format("create table %s (\n", table.getName()));

        columns.forEach(column -> sql.append(columnConstraints(column)));

        int lastChar = sql.length() - 2;
        if (sql.charAt(lastChar) == ',') {
            sql.deleteCharAt(lastChar);
        }

        sql.append(")");
        return sql.toString();
    }

    public String dropTableQuery(Table table) {
        return String.format("drop table %s", table.getName());
    }

    public String insertQuery(Table table, List<Column> columns, List<Object> values) {
        return String.format("insert into %s %s values %s", table.getName(), columnsClause(columns), valueClause(columns, values));
    }

    private String columnConstraints(Column column) {
        StringBuilder columnSql = new StringBuilder();
        columnSql.append(String.format("    %s %s", column.getName(), column.getType()));

        if (column.isPrimaryKey()) {
            columnSql.append(" PRIMARY KEY");
        }

        if (column.isAutoIncrement()) {
            columnSql.append(" AUTO_INCREMENT");
        }

        if (!column.isNullable()) {
            columnSql.append(" NOT NULL");
        }

        columnSql.append(",\n");
        return columnSql.toString();
    }

    private String columnsClause(List<Column> columns) {
        return columns.stream()
                .map(Column::getName)
                .collect(Collectors.joining(", ", "(", ")"));
    }

    private String valueClause(List<Column> columns, List<Object> values) {
        return IntStream
                .range(0, columns.size())
                .mapToObj(index -> {
                    Column column = columns.get(index);
                    Object value = values.get(index);
                    return column.isVarcharType() ? String.format("'%s'", value) : value.toString();
                }).collect(Collectors.joining(", ", "(", ")"));
    }
}
