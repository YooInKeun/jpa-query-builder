package persistence.sql.infra;

import jakarta.persistence.GenerationType;
import persistence.sql.ddl.converter.JavaToSqlMapper;

import java.util.HashMap;
import java.util.Map;

public class H2JavaToSqlMapper implements JavaToSqlMapper {

    private static final Map<Class<?>, String> javaToSqlTypeMap = new HashMap<>();

    static {
        javaToSqlTypeMap.put(String.class, "VARCHAR");
        javaToSqlTypeMap.put(Integer.class, "INTEGER");
        javaToSqlTypeMap.put(int.class, "INTEGER");
        javaToSqlTypeMap.put(Long.class, "BIGINT");
        javaToSqlTypeMap.put(long.class, "BIGINT");
        javaToSqlTypeMap.put(Double.class, "DOUBLE");
        javaToSqlTypeMap.put(double.class, "DOUBLE");
        javaToSqlTypeMap.put(Float.class, "FLOAT");
        javaToSqlTypeMap.put(float.class, "FLOAT");
        javaToSqlTypeMap.put(Boolean.class, "BOOLEAN");
        javaToSqlTypeMap.put(boolean.class, "BOOLEAN");
        javaToSqlTypeMap.put(GenerationType.IDENTITY.getClass(), "GENERATED BY DEFAULT AS IDENTITY");
        javaToSqlTypeMap.put(GenerationType.AUTO.getClass(), "GENERATED BY DEFAULT AS IDENTITY");
    }

    public String convert(Class<?> tClass) {
        return javaToSqlTypeMap.get(tClass);
    }
}
