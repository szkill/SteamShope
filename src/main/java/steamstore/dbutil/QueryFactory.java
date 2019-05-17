package steamstore.dbutil;

import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.util.concurrent.ExecutorService;

public class QueryFactory {


    private DataSource dataSource;

    public QueryFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public QueryRunner query() {
        return new QueryRunner(dataSource);
    }

    public ReThrowableQueryRunner uncheckedQuery() {
        return new ReThrowableQueryRunner(dataSource);
    }


}