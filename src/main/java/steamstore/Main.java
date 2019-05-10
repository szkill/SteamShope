package steamstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigRenderOptions;
import steamstore.connection.ConnectionPool;
import steamstore.dbutil.QueryFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
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





//        ObjectMapper mapper = new ObjectMapper();
//        PrettyPrinter prettyPrinter = new PrettyPrinter();
//
//        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
//
//        mapper
//                .enable(SerializationFeature.INDENT_OUTPUT)
//                .setDefaultPrettyPrinter(prettyPrinter)
//                .registerModule(new ParanamerModule());
//        //main.load(mapper);
//
//        ItemsServiceImpl service = new ItemsServiceImpl(new DotaDaoImpl(new File("target/DotaItem.json"), mapper), new CsGoDaoImpl(new File("target/CsGoItem.json"), mapper));
//
//
//        try {
//            DotaItem newitem = service.addDotaItem("JoskiiItem", "NoStandart", 300.0, DotaRarity.Rare.toString(), "Chen", "Украшение");
//            //DotaItem newitem = service.addDotaItem("NeJoskiiItem", "NoStandart", 300.0, DotaRarity.Mythical, "Chen", "Украшение");
//            //DotaItem newitem = service.addDotaItem("JoskiiItemNaPudge", "Standart", 300.0, DotaRarity.Rare, "Pudge", "Украшение");
//
//        } catch (NewItemException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        try {
//            CsGoItem newitem2 = service.addCsItem("Awp | Asiimov", "Field-Tested", 300.0, CsRarity.Covert.toString(), "Awp", "Noraml", "Sniper Rifle", 0.70);
//        } catch (NewItemException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        System.out.println("\n\n");
//
//
//        List<Item> allItems = service.getAllItems();
//        //List<DotaItem> allDotaItems = service.filterDotaItem("", 0.0, 300.0, "Standart", DotaRarity.Rare, "Chen", "Украшение");
//        List<DotaItem> allDotaItems = service.filterDotaItem("", 0.0, 600.0, "Standart", DotaRarity.Rare.toString(), "", "Украшение");
//        List<CsGoItem> allCsItems = service.filterCsItem("Awp | Asiimov", 200, 500.0, "Field-Tested", CsRarity.Covert.toString(), "Awp", "Noraml", "Sniper Rifle", 0.70);
//        allCsItems = service.getAllCsGoItems();
//
//
//        if (allDotaItems.size() == 0)
//            System.out.println("Список отфильтрованных предметов Dota - Пуст!");
//        else
//            System.out.println("Список отфильтрованных предметов Dota:");
//        for (DotaItem item :
//                allDotaItems) {
//            System.out.println(item.toString());
//        }
//        System.out.println("");
//
//
//        if (allCsItems.size() == 0)
//            System.out.println("Список отфильтрованных предметов Cs - Пуст!");
//        else
//            System.out.println("Список отфильтрованных предметов Cs:");
//        for (CsGoItem item :
//                allCsItems) {
//            System.out.println(item.toString());
//        }
//        System.out.println("");
//
//
//        if (allItems.size() == 0)
//            System.out.println("Список всех предметов - Пуст!");
//        else
//            System.out.println("Список всех предметов:");
//        for (Item item :
//                allItems) {
//            System.out.println(item.toString());
//        }
//        System.out.println("");
//
//
//        service.saveAllItems();
    }
}


