package steamstore.connection;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.sql.Connection;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,property = "type")
@JsonSubTypes({
        @Type(value = MySqlConnectionPool.class, name = "MySQL"),
       // @Type(value = H2ConnectionPool.class, name = "H2")
})
public interface ConnectionPool extends DataSourceOwner {

    Connection getConnection();

    void close();

}