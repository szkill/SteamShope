package steamstore.json.csgo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
public class CsGoService {


    private List<CsGoItem> items;


    public CsGoService(CsGoRepository csGoRepository) {
        items = csGoRepository.loadAll();
    }

    @SuppressWarnings("Duplicates")
    public void addItem(CsGoItem csGoItem) {
        Optional<CsGoItem> first = items.stream().filter(csGo1 -> csGo1.getName().equalsIgnoreCase(csGoItem.getName())).findFirst();
        if (!first.isPresent()) {
            this.items.add(csGoItem);
            return;
        }
        System.out.println("Предмет с таким именем уже есть");
    }

    public Optional<CsGoItem> getItemById(int id) {
        Optional<CsGoItem> first = items.stream().filter(csGo -> csGo.getId() == id).findFirst();
        return first;
    }

    public Optional<CsGoItem> getItemByName(String name) {
        Optional<CsGoItem> first = items.stream().filter(csGo -> csGo.getName().equalsIgnoreCase(name)).findFirst();
        return first;
    }

    public boolean removeItemById(int id) {
        return items.removeIf(csGo -> csGo.getId() == id);
    }

    public List<CsGoItem> getAllItems() {
        return items;
    }

    public CsGoItem getI(int i) {
        return items.get(i);
    }

    public List<CsGoItem> getItemsWithCost(double minValue, double maxValue) {
        return items.stream().filter(csGo -> csGo.getCost() >= minValue && csGo.getCost() <= maxValue).collect(Collectors.toList());
    }

    public List<CsGoItem> getItemsWithRarity(String rarity) {
        return items.stream().filter(csGo -> csGo.getRarity().equalsIgnoreCase(rarity)).collect(Collectors.toList());
    }


    public List<CsGoItem> filter(double minCost, double maxCost, String rarity, String quality) {
        List<CsGoItem> temp = items;

        temp = temp.stream()
                .filter(csGo -> {
                    if (maxCost > 0)
                        return csGo.getCost() >= minCost && csGo.getCost() <= maxCost;
                    else return csGo.getId() >= 1;
                })
                .filter(csGo1 -> {
                    if (!rarity.equals(""))
                        return csGo1.getRarity().equalsIgnoreCase(rarity);
                    else return csGo1.getId() >= 1;
                })
                .filter(csGo2 -> {
                    if (!quality.equals(""))
                        return csGo2.getQuality().equalsIgnoreCase(quality);
                    else return csGo2.getId() >= 1;
                }).collect(Collectors.toList());

        if (temp.size() == 0) {
            System.out.println("Список пуст");
        }
        return temp;

    }
}