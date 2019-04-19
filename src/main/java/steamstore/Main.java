package steamstore;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;
import steamstore.json.PrettyPrinter;
//import steamstore.json.csgo.CsGoItem;
//import steamstore.json.csgo.CsGoDao;
//import steamstore.json.csgo.CsGoService;
//import steamstore.json.csgo.CsGoDaoImpl;
import steamstore.json.csgo.CsGoDaoImpl;
import steamstore.json.dota.DotaItem;
import steamstore.json.dota.DotaDao;
import steamstore.json.dota.DotaDaoImpl;
import steamstore.service.ItemsService;
import steamstore.service.ItemsServiceImpl;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String args[]) {
        Main main = new Main();
        ObjectMapper mapper = new ObjectMapper();
        PrettyPrinter prettyPrinter = new PrettyPrinter();

        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);

        mapper
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setDefaultPrettyPrinter(prettyPrinter)
                .registerModule(new ParanamerModule());
        //main.load(mapper);

        ItemsServiceImpl service = new ItemsServiceImpl(new DotaDaoImpl(new File("target/DotaItem.json"), mapper), new CsGoDaoImpl(new File("target/CsGoItem.json"), mapper));

//        DotaItem contact = service.addDotaItem("NeJoskiiItem", "mithical", "standart", 300.0, "Chen", "Украшение");
//        List<DotaItem> allDotaItems = service.getAllDotaItems();
        List<DotaItem> allDotaItems = service.filterDotaItem("", 0.0, 200, "Pudge","Украшение", "rare", "standart");


        for (DotaItem item :
                allDotaItems) {
            System.out.println(item.toString());
        }

        service.saveAllItems();
    }
}


