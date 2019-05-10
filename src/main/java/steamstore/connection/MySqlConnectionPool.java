package steamstore.connection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.base.Preconditions;

@JsonDeserialize(builder = MySqlConnectionPool.Builder.class)
public class MySqlConnectionPool extends HikariConnectionPool {

    private MySqlConnectionPool(Builder builder) {
        super(builder);
        hikari.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                builder.getHostname(), builder.getPort(), builder.getDatabase()));
        hikari.setUsername(builder.getUsername());
        hikari.setPassword(builder.getPassword());
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder extends HikariConnectionPool.Builder<Builder> {

        private String hostname = "localhost";
        private String port = "3306";
        private String database;
        private String username = "root";
        private String password;

        private Builder() {
        }

        public Builder hostname(String hostname) {
            this.hostname = hostname;
            return this;
        }

        public Builder port(String port) {
            this.port = port;
            return this;
        }

        public Builder database(String database) {
            this.database = database;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        @Override
        public MySqlConnectionPool build() {
            return new MySqlConnectionPool(this);
        }

        private String getHostname() {
            return hostname;
        }

        private String getPort() {
            return port;
        }

        private String getDatabase() {
            Preconditions.checkState(database != null, "Database not initialized");
            return database;
        }

        private String getUsername() {
            return username;
        }

        private String getPassword() {
            Preconditions.checkState(password != null, "Password not initialized");
            return password;
        }

    }

}
