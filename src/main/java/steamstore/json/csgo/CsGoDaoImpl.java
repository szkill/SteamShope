package steamstore.json.csgo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.lang.Math;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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
    public CsGoItem create(String name, String rarity, String quality, double cost, String weapon, String itemCategory, String itemType, double floatValue) {
        CsGoItem csGoItem = new CsGoItem(idGenerator.incrementAndGet(), name, rarity, quality, cost, weapon, itemCategory, itemType, floatValue);
        allItems.put(csGoItem.getId(), csGoItem);
        return csGoItem;
    }

    @Override
    public boolean delete(long id) {
        CsGoItem remove = allItems.remove(id);
        return remove != null;
    }


    @Override
    public List<CsGoItem> filter(String name, double minCost, double maxCost, String rarity, String quality, String weapon, String itemCategory, String itemType, double floatValue) {
        List<CsGoItem> temp = getAll();
        temp = temp.stream()
                .filter(csGoItem -> {
                    if (!name.equals(""))
                        return csGoItem.getName().equalsIgnoreCase(name);
                    else return true;
                }).filter(csGoItem -> {
                    if (maxCost > 0)
                        return csGoItem.getCost() >= minCost && csGoItem.getCost() <= maxCost;
                    else return true;
                })
                .filter(csGoItem -> {
                    if (!rarity.equals(""))
                        return csGoItem.getRarity().equalsIgnoreCase(rarity);
                    else return true;
                })
                .filter(csGoItem -> {
                    if (!quality.equals(""))
                        return csGoItem.getQuality().equalsIgnoreCase(quality);
                    else return true;
                }).filter(csGoItem -> {
                    if (!weapon.equals(""))
                        return csGoItem.getWeapon().equalsIgnoreCase(weapon);
                    else return true;
                }).filter(csGoItem -> {
                    if (!itemCategory.equals(""))
                        return csGoItem.getItemCategory().equalsIgnoreCase(itemCategory);
                    else return true;
                }).filter(csGoItem -> {
                    if (!itemType.equals(""))
                        return csGoItem.getItemType().equalsIgnoreCase(itemType);
                    else return true;
                }).filter(csGoItem -> {
                    if (floatValue > 0)
                        return Math.abs(csGoItem.getFloatValue() - floatValue) <= 0.00001;
                    else return true;
                }).collect(Collectors.toList());

        if (temp.size() == 0) {
            System.out.println("Список пуст");
        }
        return temp;

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