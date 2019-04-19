package steamstore;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;
import steamstore.json.PrettyPrinter;
import steamstore.json.csgo.CsGoItem;
import steamstore.json.csgo.CsGoRepository;
import steamstore.json.csgo.CsGoService;
import steamstore.json.csgo.JsonCsGoRepository;
import steamstore.json.dota.DotaItem;
import steamstore.json.dota.DotaRepository;
import steamstore.json.dota.DotaService;
import steamstore.json.dota.JsonDotaRepository;

import java.io.File;
import java.util.*;

public class Main {

    CsGoRepository csGoRepository;
    CsGoService csGoService;
    DotaRepository dotaRepository;
    DotaService dotaService;

    public static void main(String args[]) {
        Main main = new Main();
        ObjectMapper mapper = new ObjectMapper();
        PrettyPrinter prettyPrinter = new PrettyPrinter();

        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);

        mapper
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setDefaultPrettyPrinter(prettyPrinter)
                .registerModule(new ParanamerModule());
        main.load(mapper);
    }

    private static void runid() {
        System.out.println("Предмета с таким ид не найдено");
    }

    private static void runName() {
        System.out.println("Предмета с таким именем не найдено");
    }


    private void load(ObjectMapper mapper) {

        Random random = new Random();
        csGoRepository = new JsonCsGoRepository(new File("target/CsGoItem.json"), mapper);
        csGoService = new CsGoService(csGoRepository);
        new CsGoItem(csGoService);

        List<CsGoItem> csgoAllItems = csGoService.getAllItems();

        String csArray[] = {"Керамбит| Мраморный градиент;Factory New", "AWP | Азимов;Field-Tested", "Штык-нож | Зуб тигра;Factory New"};

        ///////////   Добавление в список карточек (КС ГО)

        Arrays.stream(csArray).forEach(s ->
                csGoService.addItem(new CsGoItem(s.split(";")[0], "Тайное", s.split(";")[1],
                        random.nextInt(200), Double.parseDouble(String.format("%.2f", random.nextInt(5000) * random.nextDouble()).replace(",", "."))))
        );


        dotaRepository = new JsonDotaRepository(new File("target/DotaItem.json"), mapper);
        dotaService = new DotaService(dotaRepository);
        new DotaItem(dotaService);
        List<DotaItem> dotaItemAllItems = dotaService.getAllItems();

        ///////////   Добавление в список карточек (ДОТА)

        String dotaArray[] = {"Dragonclaw Hook;Pudge;Immortal;Standart", "The Magus Cypher;Rubick;Arcana;Exalted", "Bladeform Legacy;Juggernaut;Arcana;Exalted"};
        Arrays.stream(dotaArray).forEach(s -> {
            String[] split = s.split(";");
            dotaService.addItem(new DotaItem(split[0], split[1], split[2], split[3],
                    random.nextInt(200), Double.parseDouble(String.format("%.2f", random.nextInt(5000) * random.nextDouble()).replace(",", "."))));
        });


        //////// Получение предмета по ID

//        MyOptional.of(dotaService.getItemById(2)).ifPresent(dota2 -> {
//            try {
//                System.out.println(mapper.writeValueAsString(dota2));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }).orElse(Main::runid);


        //////// Получение предмета по названию

//        MyOptional.of(csGoService.getItemByName("awp | Азимов")).ifPresent(csGo -> {
//            try {
//                System.out.println(mapper.writeValueAsString(csGo));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }).orElse(Main::runName);

//        ///////   Удаление по ID
//        if (dotaService.removeItemById(2)) {
//            System.out.println("Успешно удалено");
//
//        } else {
//            System.out.println("Такого предмета не существует");
//        }

        /////////   Изменение по ID
//        if (!dotaService.changeItemById(2, new DotaItem("test1", "test2", "test3", "test4", 64, 197))) {
//            System.out.println("Успешно изменен");
//        } else System.out.println("Неправильный индекс");




        //      Определенный диапазон
//        dotaService.getItemsWithCost(100, 2500).forEach(dota2 -> {
//            try {
//                System.out.println(mapper.writeValueAsString(dota2));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });

        //      Точная цена
//        dotaService.getItemsWithCost(2345.28).forEach(dota2 -> {
//            try {
//                System.out.println(mapper.writeValueAsString(dota2));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });

        // Универсальный фильтр
//        dotaService.filter(40, 2500, "Arcana", "").forEach(dota2 -> {
//            try {
//                System.out.println(mapper.writeValueAsString(dota2));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });

        dotaRepository.saveAll(dotaItemAllItems);
        csGoRepository.saveAll(csgoAllItems);
    }
}


