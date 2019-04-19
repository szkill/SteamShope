package steamstore.service;

import steamstore.json.Item;
import steamstore.json.csgo.CsGoItem;
import steamstore.json.dota.DotaItem;

import java.util.List;

public class ItemsServiceImpl implements ItemsService {

    @Override
    public List<Item> getAllItems() {
        return null;
    }

    @Override
    public List<DotaItem> getAllDotaItems() {
        return null;
    }

    @Override
    public List<CsGoItem> getAllCsGoItems() {
        return null;
    }

    @Override
    public DotaItem getDotaItemById(long id) {
        return null;
    }

    @Override
    public CsGoItem getCsItemById(long id) {
        return null;
    }

    @Override
    public DotaItem addDotaItem(String name, String rarity, String quality, double cost, String hero, String itemType) {
        return null;
    }

    @Override
    public CsGoItem addCsItem(String name, String rarity, String quality, double cost, String hero, String itemType) {
        return null;
    }

    @Override
    public boolean removeDotaItem(long id) {
        return false;
    }

    @Override
    public boolean removeCsItem(long id) {
        return false;
    }

    @Override
    public List<DotaItem> filterDotaItem(String name, double minCost, double maxCost, String hero, String itemType, String rarity, String quality) {
        return null;
    }

    @Override
    public List<CsGoItem> filterCsItem(String name, double minCost, double maxCost, String hero, String itemType, String rarity, String quality) {
        return null;
    }

    @Override
    public void saveAllItems() {

    }
}
