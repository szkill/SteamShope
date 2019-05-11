package steamstore.service;

import steamstore.json.model.CsGoItem;
import steamstore.json.model.DotaItem;
import steamstore.json.model.Item;

import java.util.List;

public interface ItemsService {
    List<Item> getAllItems();

    List<DotaItem> getAllDotaItems();

    List<CsGoItem> getAllCsGoItems();

    DotaItem getDotaItemById(long id);

    CsGoItem getCsItemById(long id);

    DotaItem addDotaItem(String name, String quality, double cost, String rarity, String hero, String itemType) throws NewItemException;

    CsGoItem addCsItem(String name, String quality, double cost, String rarity, String weapon, String itemCategory, String itemType, double floatValue) throws NewItemException;

    boolean removeDotaItem(long id);

    boolean removeCsItem(long id);

    int updateCsItem(long id, String name, String quality, double cost, String rarity, String weapon, String itemCategory, String itemType, double floatValue);

    int updateDotaItem(long id,String name, String quality, double cost, String rarity, String hero, String itemType );

    public List<DotaItem> filterDotaItem(String name, double minCost, double maxCost, String quality, String rarity, String hero, String itemType);

    public List<CsGoItem> filterCsItem(String name, double minCost, double maxCost, String quality, String rarity, String weapon, String itemCategory, String itemType, double floatValue);

    public List<DotaItem> findDotaItemByName(String name);

    public List<CsGoItem> findCsItemByName(String name);


}
