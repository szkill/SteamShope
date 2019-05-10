package steamstore.connection;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class HikariConnectionPool implements ConnectionPool {

    protected final HikariDataSource hikari;

    protected <B extends Builder<B>> HikariConnectionPool(Builder<B> builder) {
        hikari = new HikariDataSource();
        hikari.setMaximumPoolSize(builder.getMaximumPoolSize());
        if (builder.hasPoolName()) {
            hikari.setPoolName(builder.getPoolName());
        }
    }

    @Override
    public final Connection getConnection() {
        try {
            return hikari.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("An exception found due getting connection:", e);
        }
    }

    @Override
    public final void close() {
        hikari.close();
    }

    @Override
    public final HikariDataSource getDataSource() {
        return hikari;
    }

    @SuppressWarnings("unchecked")
    protected static abstract class Builder<B extends Builder<B>> {

        private int maximumPoolSize = 3;
        private String poolName = null;

        protected Builder() {
        }

        private int getMaximumPoolSize() {
            return maximumPoolSize;
        }

        public B maximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
            return (B) this;
        }

        private boolean hasPoolName() {
            return poolName != null;
        }

        private String getPoolName() {
            return poolName;
        }

        public B poolName(String poolName) {
            this.poolName = poolName;
            return (B) this;
        }

        public abstract ConnectionPool build();

    }

}