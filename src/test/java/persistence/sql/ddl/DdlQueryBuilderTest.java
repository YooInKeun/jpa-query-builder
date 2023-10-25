package persistence.sql.ddl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.sql.ddl.query.DdlQueryBuilder;

class DdlQueryBuilderTest {
    final DdlQueryBuilder ddlQueryBuilder = DdlQueryBuilder.getInstance();
    final EntityMetaData entityMetaData = new EntityMetaData(Person.class);


    @Test
    @DisplayName("create table DDL query 테스트")
    void createTest() {
        String columnsDdl = ddlQueryBuilder.createTable(entityMetaData);
        Assertions.assertThat(columnsDdl.trim().toLowerCase())
                .isEqualTo("create table users (old integer, nick_name varchar(255), id bigint auto_increment primary key, email varchar(255) not null )");
    }

    @Test
    @DisplayName("drop table 테스트")
    void dropTest() {
        String columnsDdl = ddlQueryBuilder.dropTable(entityMetaData);
        Assertions.assertThat(columnsDdl.trim().toLowerCase())
                .isEqualTo("drop table users");
    }
}