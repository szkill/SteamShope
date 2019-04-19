package steamstore.json.dao;

import steamstore.json.model.CsGoItem;
import steamstore.json.model.enums.CsRarity;

import java.util.List;

public interface CsGoDao {

    List<CsGoItem> getAll();

    CsGoItem getById(long id);

    CsGoItem create(String name, String quality, double cost, CsRarity rarity, String weapon, String itemCategory, String itemType, double floatValue);

    boolean delete(long id);


    public List<CsGoItem> filter(String name, double minCost, double maxCost, String quality, CsRarity rarity, String weapon, String itemCategory, String itemType, double floatValue);



    List<CsGoItem> loadAll();

    void saveAll();

}