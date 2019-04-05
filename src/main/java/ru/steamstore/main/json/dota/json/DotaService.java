package ru.steamstore.main.json.dota.json;

import ru.steamstore.main.utils.MyOptional;

import java.util.List;
import java.util.Optional;

public class DotaService {


    private List<Dota2> items;


    public DotaService(DotaRepository dotaRepository) {
        items = dotaRepository.loadAll();
    }

    @SuppressWarnings("Duplicates")
    public void addItem(Dota2 dota2) {
        Optional<Dota2> first = items.stream().filter(dota21 -> dota21.getName().equalsIgnoreCase(dota2.getName())).findFirst();
        if (!first.isPresent()) {
            this.items.add(dota2);
            return;
        }
        System.out.println("Предмет с таким именем уже есть");

    }

    public Optional<Dota2> getItemById(int id) {
        Optional<Dota2> first = items.stream().filter(dota2 -> dota2.getId() == id).findFirst();
        return first;
    }

    public Optional<Dota2> getItemByName(String name) {
        Optional<Dota2> first = items.stream().filter(dota2 -> dota2.getName().equalsIgnoreCase(name)).findFirst();
        return first;
    }

    public List<Dota2> getAllItems() {
        return items;
    }

    public Dota2 getI(int i) {
        return items.get(i);
    }

    public boolean removeItemById(int id) {
        return items.removeIf(dota2 -> dota2.getId() == id);
    }

    public boolean changeItemById(int id, Dota2 dota2) {
        boolean[] flag = {true};
        MyOptional.of(getItemById(id)).ifPresent(dota21 -> {
            removeItemById(id);
            flag[0] = false;
            addItem(new Dota2(id, dota2.getName(), dota2.getUsability(), dota2.getRarity(), dota2.getQuality(), dota2.getCount(), dota2.getCost()));
        });
        return flag[0];
    }


}