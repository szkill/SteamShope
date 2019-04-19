package steamstore.json.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import steamstore.json.dao.CsGoDao;
import steamstore.json.model.CsGoItem;
import steamstore.json.model.enums.CsRarity;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.lang.Math;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsGoDaoImpl implements CsGoDao {

    private final File file;
    private final ObjectMapper objectMapper;

    private AtomicLong idGenerator = new AtomicLong();
    private Map<Long, CsGoItem> allItems = new HashMap<>();


    public CsGoDaoImpl(File file, ObjectMapper objectMapper) {
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
        List<CsGoItem> csGoItems = this.loadAll();
        for (CsGoItem item :
                csGoItems) {
            CsGoItem csGoItem = new CsGoItem(idGenerator.incrementAndGet(), item);
            allItems.put(csGoItem.getId(), csGoItem);
        }
    }

    @Override
    public List<CsGoItem> getAll() {
        return new ArrayList<>(allItems.values());
    }

    @Override
    public CsGoItem getById(long id) {
        return allItems.get(id);
    }

    @Override
    public CsGoItem create(String name, String quality, double cost, CsRarity rarity, String weapon, String itemCategory, String itemType, double floatValue) {
        CsGoItem csGoItem = new CsGoItem(idGenerator.incrementAndGet(), name, quality, cost, rarity, weapon, itemCategory, itemType, floatValue);
        allItems.put(csGoItem.getId(), csGoItem);
        return csGoItem;
    }

    @Override
    public boolean delete(long id) {
        CsGoItem remove = allItems.remove(id);
        return remove != null;
    }


    @Override
    public List<CsGoItem> filter(String name, double minCost, double maxCost, String quality, CsRarity rarity, String weapon, String itemCategory, String itemType, double floatValue) {
        Stream<CsGoItem> temp = getAll().stream();
        if (!name.equals(""))
            temp = temp.filter(csGoItem -> csGoItem.getName().equalsIgnoreCase(name));
        if (maxCost >= 0 && minCost >= 0)
            temp = temp.filter(csGoItem -> csGoItem.getCost() >= minCost && csGoItem.getCost() <= maxCost);
        if (rarity != CsRarity.Any)
            temp = temp.filter(csGoItem -> csGoItem.getRarity() == rarity);
        if (!quality.equals(""))
            temp = temp.filter(csGoItem -> csGoItem.getQuality().equalsIgnoreCase(quality));
        if (!weapon.equals(""))
            temp = temp.filter(csGoItem -> csGoItem.getWeapon().equalsIgnoreCase(weapon));
        if (!itemCategory.equals(""))
            temp = temp.filter(csGoItem -> csGoItem.getItemCategory().equalsIgnoreCase(itemCategory));
        if (!itemType.equals(""))
            temp = temp.filter(csGoItem -> csGoItem.getItemType().equalsIgnoreCase(itemType));
        if (floatValue > 0)
            temp = temp.filter(csGoItem -> Math.abs(csGoItem.getFloatValue() - floatValue) <= 0.00001);


        List<CsGoItem> result = temp.collect(Collectors.toList());
        if (result.size() == 0) {
            System.out.println("Список пуст");
        }

        return result;
    }


    @SuppressWarnings("Duplicates")
    @Override
    public List<CsGoItem> loadAll() {
        if (file.length() == 0) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<CsGoItem>>() {
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