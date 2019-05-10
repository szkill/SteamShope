package steamstore.connection;

import javax.sql.DataSource;

public interface DataSourceOwner {

    DataSource getDataSource();

}