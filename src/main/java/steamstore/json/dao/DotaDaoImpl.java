package steamstore.json.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import steamstore.json.model.DotaItem;
import steamstore.json.model.enums.DotaRarity;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
    public DotaItem create(String name, String quality, double cost, String rarity, String hero, String itemType) {
        DotaItem dotaItem = new DotaItem(idGenerator.incrementAndGet(), name, quality, cost, rarity, hero, itemType);
        allItems.put(dotaItem.getId(), dotaItem);
        return dotaItem;
    }

    @Override
    public boolean delete(long id) {
        DotaItem remove = allItems.remove(id);
        return remove != null;
    }


    @Override
    public List<DotaItem> filter(String name, double minCost, double maxCost, String quality, String rarity, String hero, String itemType) {
        Stream<DotaItem> temp = getAll().stream();
        if (!name.equals(""))
            temp = temp.filter(dotaItem -> dotaItem.getName().equalsIgnoreCase(name));
        if (maxCost >= 0 && minCost >= 0)
            temp = temp.filter(dotaItem -> dotaItem.getCost() >= minCost - 0.0001 && dotaItem.getCost() <= maxCost + 0.00001);
        if (rarity != DotaRarity.Any.toString())
            temp = temp.filter(dotaItem -> dotaItem.getRarity() == rarity);
        if (!quality.equals(""))
            temp = temp.filter(dotaItem -> dotaItem.getQuality().equalsIgnoreCase(quality));
        if (!hero.equals(""))
            temp = temp.filter(dotaItem -> dotaItem.getHero().equalsIgnoreCase(hero));
        if (!itemType.equals(""))
            temp = temp.filter(dotaItem -> dotaItem.getItemType().equalsIgnoreCase(itemType));

//        List<DotaItem> result = temp.collect(Collectors.toList());
//        if (result.size() == 0) {
//            System.out.println("Список пуст");
//        }

        return temp.collect(Collectors.toList());

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