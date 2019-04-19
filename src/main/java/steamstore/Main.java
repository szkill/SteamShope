package steamstore;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;
import steamstore.json.PrettyPrinter;
//import steamstore.json.model.CsGoItem;
//import steamstore.json.dao.CsGoDao;
//import steamstore.json.csgo.CsGoService;
//import steamstore.json.dao.CsGoDaoImpl;
import steamstore.json.dao.CsGoDaoImpl;
import steamstore.json.model.DotaItem;
import steamstore.json.dao.DotaDaoImpl;
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

        DotaItem contact = service.addDotaItem("NeJoskiiItem", "mithical", "standart", 49.0, "Chen", "Украшение222");
      //  List<DotaItem> allDotaItems = service.getAllDotaItems();
         List<DotaItem> allDotaItems = service.filterDotaItem("", 0.0, 300.0, "rare", "standart", "Pudge", "Украшение");


        for (DotaItem item :
                allDotaItems) {
            System.out.println(item.toString());
        }

        service.saveAllItems();
    }
}


