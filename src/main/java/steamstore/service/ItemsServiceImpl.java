package steamstore.service;

import steamstore.json.model.Item;
import steamstore.json.dao.CsGoDao;
import steamstore.json.model.CsGoItem;
import steamstore.json.dao.DotaDao;
import steamstore.json.model.DotaItem;
import steamstore.json.model.enums.CsRarity;
import steamstore.json.model.enums.DotaRarity;

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
    public DotaItem addDotaItem(String name, String quality, double cost, DotaRarity rarity, String hero, String itemType) {
        return dotaDao.create(name, quality, cost, rarity, hero, itemType);
    }

    @Override
    public CsGoItem addCsItem(String name, String quality, double cost, CsRarity rarity, String weapon, String itemCategory, String itemType, double floatValue) {
        return csGoDao.create(name, quality, cost, rarity, weapon, itemCategory, itemType, floatValue);
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
    public List<DotaItem> filterDotaItem(String name, double minCost, double maxCost, String quality, DotaRarity rarity, String hero, String itemType) {
        return dotaDao.filter(name, minCost, maxCost, quality, rarity, hero, itemType);
    }

    @Override
    public List<CsGoItem> filterCsItem(String name, double minCost, double maxCost, String quality, CsRarity rarity, String weapon, String itemCategory, String itemType, double floatValue) {
        return csGoDao.filter(name, minCost, maxCost, quality, rarity, weapon, itemCategory, itemType, floatValue);
    }

    @Override
    public List<DotaItem> findDotaItemByName(String name) {
        return filterDotaItem(name, Integer.MIN_VALUE, Integer.MIN_VALUE + 1, "", DotaRarity.Any, "", "");
    }

    @Override
    public List<CsGoItem> findCsItemByName(String name) {
        return filterCsItem(name, Integer.MIN_VALUE, Integer.MIN_VALUE + 1, "", CsRarity.Any, "", "","", Double.MIN_VALUE);
    }

    @Override
    public void saveAllItems() {
        dotaDao.saveAll();
        csGoDao.saveAll();
    }
}
