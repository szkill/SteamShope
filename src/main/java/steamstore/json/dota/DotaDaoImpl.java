package steamstore.json.dota;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import steamstore.json.Games;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class DotaDaoImpl implements DotaDao {

    private final File file;
    private final ObjectMapper objectMapper;

    private AtomicLong idGenerator = new AtomicLong();
    private Map<Long, DotaItem> allItems = new HashMap<>();


    public DotaDaoImpl(File file, ObjectMapper objectMapper) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (file.length() == 0) {
            try {
                List<String> s1 = new ArrayList<>();
                objectMapper.writeValue(file, s1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.file = file;
        this.objectMapper = objectMapper;

        //Тут мб надо переписать
        List<DotaItem> dotaItems = this.loadAll();
        for (DotaItem item :
                dotaItems) {
            DotaItem dotaItem = new DotaItem(idGenerator.incrementAndGet(), item);
            allItems.put(dotaItem.getId(), dotaItem);
        }
    }

    @Override
    public List<DotaItem> getAll() {
        return new ArrayList<>(allItems.values());
    }

    @Override
    public DotaItem getById(long id) {
        return allItems.get(id);
    }

    @Override
    public DotaItem create(String name, String rarity, String quality, double cost, String hero, String itemType) {
        DotaItem dotaItem = new DotaItem(idGenerator.incrementAndGet(), name, rarity, quality, cost, hero, itemType);
        allItems.put(dotaItem.getId(), dotaItem);
        return dotaItem;
    }

    @Override
    public boolean delete(long id) {
        DotaItem remove = allItems.remove(id);
        return remove != null;
    }


    @Override
    public List<DotaItem> filter(String name, double minCost, double maxCost, String rarity, String quality, String hero, String itemType) {
        List<DotaItem> temp = getAll();
        temp = temp.stream()
                .filter(dotaItem -> {
                    if (!name.equals(""))
                        return dotaItem.getName().equalsIgnoreCase(name);
                    else return true;
                }).filter(dotaItem -> {
                    if (maxCost > 0)
                        return dotaItem.getCost() >= minCost && dotaItem.getCost() <= maxCost;
                    else return true;
                })
                .filter(dotaItem -> {
                    if (!rarity.equals(""))
                        return dotaItem.getRarity().equalsIgnoreCase(rarity);
                    else return true;
                })
                .filter(dotaItem -> {
                    if (!quality.equals(""))
                        return dotaItem.getQuality().equalsIgnoreCase(quality);
                    else return true;
                }).filter(dotaItem -> {
                    if (!hero.equals(""))
                        return dotaItem.getHero().equalsIgnoreCase(hero);
                    else return true;
                }).filter(dotaItem -> {
                    if (!itemType.equals(""))
                        return dotaItem.getItemType().equalsIgnoreCase(itemType);
                    else return true;
                }).collect(Collectors.toList());

        if (temp.size() == 0) {
            System.out.println("Список пуст");
        }
        return temp;

    }




    @SuppressWarnings("Duplicates")
    @Override
    public List<DotaItem> loadAll() {
        if (file.length() == 0) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<DotaItem>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAll() {
        try {
            objectMapper.writeValue(file, getAll());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}