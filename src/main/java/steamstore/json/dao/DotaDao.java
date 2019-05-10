package steamstore.json.dao;

import steamstore.json.model.DotaItem;
import steamstore.json.model.enums.DotaRarity;

import java.util.List;

public interface DotaDao {

    List<DotaItem> getAll();

    DotaItem getById(long id);

    DotaItem create(String name, String quality, double cost, String rarity, String hero, String itemType);

    boolean delete(long id);


    List<DotaItem> filter(String name, double minCost, double maxCost, String quality, String rarity, String hero, String itemType);


    List<DotaItem> loadAll();

    void saveAll();

}