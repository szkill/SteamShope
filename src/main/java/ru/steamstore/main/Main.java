package ru.steamstore.main;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.paranamer.ParanamerModule;
import ru.steamstore.main.json.PrettyPrinter;
import ru.steamstore.main.json.csgo.json.CsGo;
import ru.steamstore.main.json.csgo.json.CsGoRepository;
import ru.steamstore.main.json.csgo.json.CsGoService;
import ru.steamstore.main.json.csgo.json.JsonCsGoRepository;
import ru.steamstore.main.json.dota.json.Dota2;
import ru.steamstore.main.json.dota.json.DotaRepository;
import ru.steamstore.main.json.dota.json.DotaService;
import ru.steamstore.main.json.dota.json.JsonDotaRepository;
import ru.steamstore.main.utils.MyOptional;

import java.io.File;
import java.io.IOException;
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
        csGoRepository = new JsonCsGoRepository(new File("target/CsGo.json"), mapper);
        csGoService = new CsGoService(csGoRepository);
        new CsGo(csGoService);

        List<CsGo> csgoAllItems = csGoService.getAllItems();

        String csArray[] = {"Керамбит| Мраморный градиент;Factory New", "AWP | Азимов;Field-Tested", "Штык-нож | Зуб тигра;Factory New"};

        Arrays.stream(csArray).forEach(s ->
                csGoService.addItem(new CsGo(s.split(";")[0], "Тайное", s.split(";")[1],
                        random.nextInt(200), Double.parseDouble(String.format("%.2f", random.nextInt(5000) * random.nextDouble()).replace(",", "."))))
        );

//        csGoService.addItem(new CsGo("Керамбит| Мраморный градиент", "Тайное", "Factory New",
//                random.nextInt(200), Double.parseDouble(String.format("%.2f", random.nextInt(5000) * random.nextDouble()).replace(",", "."))));


        dotaRepository = new JsonDotaRepository(new File("target/Dota2.json"), mapper);
        dotaService = new DotaService(dotaRepository);
        new Dota2(dotaService);
        List<Dota2> dota2AllItems = dotaService.getAllItems();

        String dotaArray[] = {"Dragonclaw Hook;Pudge;Immortal;Standart", "The Magus Cypher;Rubick;Arcana;Exalted", "Bladeform Legacy;Juggernaut;Arcana;Exalted"};
        Arrays.stream(dotaArray).forEach(s -> {
            String[] split = s.split(";");
            dotaService.addItem(new Dota2(split[0], split[1], split[2], split[3],
                    random.nextInt(200), Double.parseDouble(String.format("%.2f", random.nextInt(5000) * random.nextDouble()).replace(",", "."))));
        });


//        MyOptional.of(dotaService.getItemById(2)).ifPresent(dota2 -> {
//            try {
//                System.out.println(mapper.writeValueAsString(dota2));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }).orElse(Main::runid);


//        MyOptional.of(csGoService.getItemByName("awp | Азимов")).ifPresent(csGo -> {
//            try {
//                System.out.println(mapper.writeValueAsString(csGo));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }).orElse(Main::runName);


//        if (dotaService.removeItemById(2)) {
//            System.out.println("Успешно удалено");
//        } else {
//            System.out.println("Такого предмета не существует");
//        }

//        if (dotaService.changeItemById(64, new Dota2("test1", "test2", "test3", "test4", 228, 1488))) {
//            System.out.println("Успешно изменен");
//        }
//        else System.out.println("Неправильный индекс");

        dotaRepository.saveAll(dota2AllItems);
        csGoRepository.saveAll(csgoAllItems);
    }
}


