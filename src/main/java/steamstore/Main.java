package steamstore;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;
import steamstore.exception.DotaServiceException;
import steamstore.json.PrettyPrinter;
import steamstore.json.dao.*;
import steamstore.json.model.CsGoItem;
import steamstore.json.model.DotaItem;
import steamstore.json.model.Item;
import steamstore.json.model.enums.CsRarity;
import steamstore.json.model.enums.DotaRarity;
import steamstore.service.H2DotaServiceImpl;
import steamstore.service.ItemsServiceImpl;
import steamstore.service.NewItemException;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String args[]) throws IOException, SQLException, ClassNotFoundException {
        Main main = new Main();
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/dbname", "user", "password");

        DataSource dataSource = ConnectionManager.createDataSource();

        // H2DotaServiceImpl service1 = new H2DotaServiceImpl(new DotaDaoH2Impl(dataSource));
        ItemsServiceImpl service = new ItemsServiceImpl
                (new DotaDaoH2Impl(dataSource), new CsGoDaoH2Impl(dataSource));

        try {

            try {
                service.addDotaItem("JoskiiItem3", "NoStandart", 400.0, DotaRarity.Rare.toString(), "Chen", "Украшение");
            } catch (NewItemException ex) {
                System.out.println(ex.getMessage());
            }

//
//            for (DotaItem dotaItem : service.getAllDotaItems()) {
//                System.out.println(dotaItem);
//            }
        } catch (DotaServiceException e) {
            System.err.println(e);
        }

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
//        List<DotaItem> allDotaItems = service.filterDotaItem("", 0.0, 300.0, "Standart", DotaRarity.Rare.toString(), "", "Украшение");
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


