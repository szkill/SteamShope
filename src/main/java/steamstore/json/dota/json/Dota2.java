package steamstore.json.dota.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import steamstore.json.Item;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Dota2 extends Item {

    private static DotaService dotaService;
    private String usability;

    public Dota2(DotaService dotaService) {
        super();
        this.dotaService = dotaService;
    }

    @JsonCreator
    public Dota2(int id, String name, String usability, String rarity, String quality, int count, double cost) {
        super(name, rarity, quality, count, cost);
        setId(id);
        this.usability = usability;
    }

    @JsonCreator
    public Dota2(String name, String usability, String rarity, String quality, int count, double cost) {
        super(name, rarity, quality, count, cost);
        int index;
        try {
            Optional<Dota2> first = dotaService.getAllItems().stream().findFirst();
            if (!first.isPresent()) {
                index = 1;
            } else {
                List<Dota2> dota2AllItems = dotaService.getAllItems();
                index = dota2AllItems.stream().max(Comparator.comparingInt(Item::getId)).get().getId() + 1;
            }
            setId(index);
        } catch (Exception e) {

        }
        this.usability = usability;
    }




    public String getUsability() {
        return usability;
    }

}
