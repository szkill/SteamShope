package steamstore.dbutil;

import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.util.concurrent.ExecutorService;

public class QueryFactory {

    private ExecutorService executorService;
    private DataSource dataSource;

    public QueryFactory(ExecutorService executorService, DataSource dataSource) {
        this.executorService = executorService;
        this.dataSource = dataSource;
    }

    public QueryRunner query() {
        return new QueryRunner(dataSource);
    }

    public ReThrowableQueryRunner uncheckedQuery() {
        return new ReThrowableQueryRunner(dataSource);
    }


}