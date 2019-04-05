package ru.steamstore.main.json.csgo.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import ru.steamstore.main.json.Item;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CsGo extends Item {
    public static CsGoService csGoService;

    public CsGo(CsGoService csGoService) {
        super();
        this.csGoService = csGoService;
    }
    @JsonCreator
    public CsGo(String name, String rarity, String quality, int count, double cost) {
        super(name, rarity, quality, count, cost);
        int index;
        try {
            Optional<CsGo> first = csGoService.getAllItems().stream().findFirst();
            if (!first.isPresent()) {
                index = 1;
            } else {
                List<CsGo> csgoAllItems = csGoService.getAllItems();
                index = csgoAllItems.stream().max(Comparator.comparingInt(Item::getId)).get().getId() + 1;
            }
            setId(index);
        } catch (Exception e) {

        }

    }
}
