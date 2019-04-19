package steamstore.json.csgo;

import com.fasterxml.jackson.annotation.JsonCreator;
import steamstore.json.Item;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CsGoItem extends Item {
    public static CsGoService csGoService;

    public CsGoItem(CsGoService csGoService) {
        super();
        this.csGoService = csGoService;
    }
    @JsonCreator
    public CsGoItem(String name, String rarity, String quality, int count, double cost) {
        super(name, rarity, quality, count, cost);
        int index;
        try {
            Optional<CsGoItem> first = csGoService.getAllItems().stream().findFirst();
            if (!first.isPresent()) {
                index = 1;
            } else {
                List<CsGoItem> csgoAllItems = csGoService.getAllItems();
                index = csgoAllItems.stream().max(Comparator.comparingInt(Item::getId)).get().getId() + 1;
            }
            setId(index);
        } catch (Exception e) {

        }

    }
}
