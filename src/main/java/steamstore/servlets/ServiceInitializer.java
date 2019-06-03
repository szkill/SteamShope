package steamstore.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;
import steamstore.Main;
import steamstore.connection.ConnectionPool;
import steamstore.dbutil.QueryFactory;
import steamstore.json.dao.CsGoDaoMySqlImpl;
import steamstore.json.dao.DotaDaoMySqlImpl;
import steamstore.json.dao.UserDaoImpl;
import steamstore.json.model.Item;
import steamstore.service.ItemsService;
import steamstore.service.ItemsServiceImpl;
import steamstore.service.UserService;
import steamstore.service.UserServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@SuppressWarnings("Duplicates")
@WebListener
public class ServiceInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Config dbProperties;
            try (InputStream is = Main.class.getClassLoader().getResourceAsStream("db.properties");
                 BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                dbProperties = ConfigFactory.parseReader(reader);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            ConnectionPool connectionPool = objectMapper.readValue(
                    dbProperties.root().render(ConfigRenderOptions.defaults().setComments(false).setOriginComments(false)),
                    ConnectionPool.class
            );


            QueryFactory queryFactory = new QueryFactory(connectionPool.getDataSource());

            UserServiceImpl userService = new UserServiceImpl(new UserDaoImpl(queryFactory));
            ItemsServiceImpl service = new ItemsServiceImpl
                    (new DotaDaoMySqlImpl(queryFactory), new CsGoDaoMySqlImpl(queryFactory));
            // List<Item> allItems = service.getAllItems();
            sce.getServletContext().setAttribute(ItemsService.SERVICE_NAME, service);
            sce.getServletContext().setAttribute(UserService.SERVICE_NAME, userService);

        } catch (IOException e) {
            throw new RuntimeException("Unable to initialize contacts service");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
