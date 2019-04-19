package steamstore.json.dao;

import steamstore.json.model.CsGoItem;

import java.util.List;

public interface CsGoDao {

    List<CsGoItem> getAll();

    CsGoItem getById(long id);

    CsGoItem create(String name, String rarity, String quality, double cost, String weapon, String itemCategory, String itemType, double floatValue);

    boolean delete(long id);


    public List<CsGoItem> filter(String name, double minCost, double maxCost, String rarity, String quality, String weapon, String itemCategory, String itemType, double floatValue);



    List<CsGoItem> loadAll();

    void saveAll();

}