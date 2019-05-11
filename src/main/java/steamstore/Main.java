package steamstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;
import steamstore.connection.ConnectionPool;
import steamstore.dbutil.QueryFactory;
import steamstore.exception.DotaServiceException;
import steamstore.json.model.Item;
import steamstore.json.model.enums.CsRarity;
import steamstore.json.dao.CsGoDaoMySqlImpl;
import steamstore.json.dao.DotaDaoMySqlImpl;
import steamstore.json.model.enums.DotaRarity;
import steamstore.service.ItemsServiceImpl;
import steamstore.service.NewItemException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
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
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        QueryFactory queryFactory = new QueryFactory(executorService, connectionPool.getDataSource());


        ItemsServiceImpl service = new ItemsServiceImpl
                (new DotaDaoMySqlImpl(queryFactory), new CsGoDaoMySqlImpl(queryFactory));
        List<Item> allItems = service.getAllItems();


        try {

            try {
                service.addDotaItem("JoskiiItem", "NoStandart", 300.0, DotaRarity.Rare.toString(), "Chen", "Украшение");
                service.addDotaItem("NeJoskiiItem", "NoStandart", 300.0, DotaRarity.Mythical.toString(), "Chen", "Украшение");
                service.addDotaItem("JoskiiItemNaPudge", "Standart", 300.0, DotaRarity.Rare.toString(), "Pudge", "Украшение");

            } catch (NewItemException ex) {
                System.out.println(ex.getMessage());
            }
//
            try {
                service.addCsItem("Awp | Asiimov", "Field-Tested", 300.0, CsRarity.Covert.toString(), "Awp", "Noraml", "Sniper Rifle", 0.70);
            } catch (NewItemException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (DotaServiceException e) {
            System.err.println(e);
        }
        System.out.println("\n\n");

        service.getAllDotaItems().forEach(dotaItem -> System.out.println(dotaItem.toString()));

        // System.out.println(service.removeDotaItem(2));

//        System.out.println(service.updateDotaItem(3, "JoskiiItemTest", "NoStandart", 400.0,
//                DotaRarity.Rare.toString(), "Chen", "Украшение"));
//
//
//        System.out.println(service.updateCsItem(1, "Awp2 | Asiimov", "Field-Tested", 188.0,
//                CsRarity.Covert.toString(), "Awp", "Noraml", "Sniper Rifle1", 0.70));



    }
}


