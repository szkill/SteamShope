package steamstore.service;

import steamstore.json.Item;
import steamstore.json.csgo.CsGoItem;
import steamstore.json.dota.DotaItem;

import java.util.List;

public interface ItemsService {
    List<Item> getAllItems();
    List<DotaItem> getAllDotaItems();
    List<CsGoItem> getAllCsGoItems();

    DotaItem getDotaItemById(long id);
    CsGoItem getCsItemById(long id);

    DotaItem addDotaItem(String name, String rarity, String quality, double cost, String hero, String itemType);
    CsGoItem addCsItem(String name, String rarity, String quality, double cost, String hero, String itemType);

    boolean removeDotaItem(long id);
    boolean removeCsItem(long id);


    public List<DotaItem> filterDotaItem(String name, double minCost, double maxCost, String hero, String itemType, String rarity, String quality);
    public List<CsGoItem> filterCsItem(String name, double minCost, double maxCost, String hero, String itemType, String rarity, String quality);

    void saveAllItems();
}
