package steamstore.json.dao;

import steamstore.json.model.DotaItem;
import steamstore.json.model.enums.DotaRarity;

import java.util.List;

public interface DotaDao {

    List<DotaItem> getAll();

    DotaItem getById(long id);

    DotaItem create(String name, String quality, double cost, DotaRarity rarity, String hero, String itemType);

    boolean delete(long id);


    public List<DotaItem> filter(String name, double minCost, double maxCost, String quality, DotaRarity rarity, String hero, String itemType);



    List<DotaItem> loadAll();

    void saveAll();

}