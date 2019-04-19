package steamstore.json.dota;

import steamstore.utils.MyOptional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
public class DotaService {


    private List<DotaItem> items;
    private DotaService dotaService;

    public DotaService(DotaDao dotaRepository) {
        items = dotaRepository.loadAll();
    }

    @SuppressWarnings("Duplicates")
    public void addItem(DotaItem dotaItem) {
        Optional<DotaItem> first = items.stream().filter(dota21 -> dota21.getName().equalsIgnoreCase(dotaItem.getName())).findFirst();
        if (!first.isPresent()) {
            this.items.add(dotaItem);
            return;
        }
        System.out.println("Предмет с таким именем уже есть");

    }

    public Optional<DotaItem> getItemById(int id) {
        Optional<DotaItem> first = items.stream().filter(dota2 -> dota2.getId() == id).findFirst();
        return first;
    }

    public Optional<DotaItem> getItemByName(String name) {
        Optional<DotaItem> first = items.stream().filter(dota2 -> dota2.getName().equalsIgnoreCase(name)).findFirst();
        return first;
    }

    public List<DotaItem> getAllItems() {
        return items;
    }

    public DotaItem getI(int i) {
        return items.get(i);
    }

    public boolean removeItemByIdForChange(int id) {
        return items.removeIf(dota2 -> dota2.getId() == id);
    }

    public boolean removeItemById(int id) {
        if (items.removeIf(dota2 -> dota2.getId() == id)) {
            items.stream().forEach(dota2 -> {
                if (dota2.getId() > id) {
                    dota2.setId(dota2.getId() - 1);
                }
            });
            return true;
        } else return false;
    }

    public boolean changeItemById(int id, DotaItem dotaItem) {
        boolean[] flag = {true};
        MyOptional.of(getItemById(id)).ifPresent(d -> {
            removeItemByIdForChange(id);
            flag[0] = false;
            addItem(new DotaItem(id, dotaItem.getName(), dotaItem.getUsability(), dotaItem.getRarity(), dotaItem.getQuality(), dotaItem.getCount(), dotaItem.getCost()));
        });
        return flag[0];
    }

//    public List<DotaItem> getItemsWithCost(double cost) {
//        return items.stream().filter(dota2 -> dota2.getCost() == cost).collect(Collectors.toList());
//    }

    public List<DotaItem> getItemsWithCost(double minValue, double maxValue) {
        return items.stream().filter(dota2 -> dota2.getCost() >= minValue && dota2.getCost() <= maxValue).collect(Collectors.toList());
    }

    public List<DotaItem> getItemsWithRarity(String rarity) {
        return items.stream().filter(dota2 -> dota2.getRarity().equalsIgnoreCase(rarity)).collect(Collectors.toList());
    }


    public List<DotaItem> filter(double minCost, double maxCost, String rarity, String quality) {
        List<DotaItem> temp = items;
        temp = temp.stream()
                .filter(dota2 -> {
                    if (maxCost > 0)
                        return dota2.getCost() >= minCost && dota2.getCost() <= maxCost;
                    else return dota2.getId() >= 1;
                })
                .filter(dota2 -> {
                    if (!rarity.equals(""))
                        return dota2.getRarity().equalsIgnoreCase(rarity);
                    else return dota2.getId() >= 1;
                })
                .filter(dota2 -> {
                    if (!quality.equals(""))
                        return dota2.getQuality().equalsIgnoreCase(quality);
                    else return dota2.getId() >= 1;
                }).collect(Collectors.toList());

        if (temp.size() == 0) {
            System.out.println("Список пуст");
        }
        return temp;

    }


}