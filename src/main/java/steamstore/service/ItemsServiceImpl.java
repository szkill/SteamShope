package steamstore.service;

import steamstore.json.model.Item;
import steamstore.json.dao.CsGoDao;
import steamstore.json.model.CsGoItem;
import steamstore.json.dao.DotaDao;
import steamstore.json.model.DotaItem;

import java.util.ArrayList;
import java.util.List;

public class ItemsServiceImpl implements ItemsService {
    private final DotaDao dotaDao;
    private final CsGoDao csGoDao;

    public ItemsServiceImpl(DotaDao dotaDao, CsGoDao csGoDao) {
        this.dotaDao = dotaDao;
        this.csGoDao = csGoDao;
    }

    @Override
    public List<Item> getAllItems() {
        //Почему-то по другому нормально нельзя
        List<Item> allItems = new ArrayList();
        for (DotaItem item :
                dotaDao.getAll()) {
            allItems.add(item);
        }
        for (CsGoItem item :
                csGoDao.getAll()) {
            allItems.add(item);
        }

        return allItems;
    }

    @Override
    public List<DotaItem> getAllDotaItems() {
        return dotaDao.getAll();
    }

    @Override
    public List<CsGoItem> getAllCsGoItems() {
        return csGoDao.getAll();
    }

    @Override
    public DotaItem getDotaItemById(long id) {
        return dotaDao.getById(id);
    }

    @Override
    public CsGoItem getCsItemById(long id) {
        return csGoDao.getById(id);
    }

    @Override
    public DotaItem addDotaItem(String name, String rarity, String quality, double cost, String hero, String itemType) {
        return dotaDao.create(name, rarity, quality, cost, hero, itemType);
    }

    @Override
    public CsGoItem addCsItem(String name, String rarity, String quality, double cost, String weapon, String itemCategory, String itemType, double floatValue) {
        return null;
    }

    @Override
    public boolean removeDotaItem(long id) {
        return dotaDao.delete(id);
    }

    @Override
    public boolean removeCsItem(long id) {
        return false;
    }

    @Override
    public List<DotaItem> filterDotaItem(String name, double minCost, double maxCost, String rarity, String quality, String hero, String itemType) {
        return dotaDao.filter(name, minCost, maxCost, rarity, quality, hero, itemType);
    }

    @Override
    public List<CsGoItem> filterCsItem(String name, double minCost, double maxCost, String rarity, String quality, String weapon, String itemCategory, String itemType, double floatValue) {
        return csGoDao.filter(name, minCost, maxCost, rarity, quality, weapon, itemCategory, itemType, floatValue);
    }

    @Override
    public void saveAllItems() {
        dotaDao.saveAll();
        csGoDao.saveAll();
    }
}
