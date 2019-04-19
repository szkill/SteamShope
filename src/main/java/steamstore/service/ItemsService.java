package steamstore.service;

import steamstore.json.model.Item;
import steamstore.json.model.CsGoItem;
import steamstore.json.model.DotaItem;

import java.util.List;

public interface ItemsService {
    List<Item> getAllItems();
    List<DotaItem> getAllDotaItems();
    List<CsGoItem> getAllCsGoItems();

    DotaItem getDotaItemById(long id);
    CsGoItem getCsItemById(long id);

    DotaItem addDotaItem(String name, String rarity, String quality, double cost, String hero, String itemType);
    CsGoItem addCsItem(String name, String rarity, String quality, double cost, String weapon, String itemCategory, String itemType, double floatValue);

    boolean removeDotaItem(long id);
    boolean removeCsItem(long id);


    public List<DotaItem> filterDotaItem(String name, double minCost, double maxCost, String rarity, String quality, String hero, String itemType);
    public List<CsGoItem> filterCsItem(String name, double minCost, double maxCost, String rarity, String quality, String weapon, String itemCategory, String itemType, double floatValue);

    void saveAllItems();
}
