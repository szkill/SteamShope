package steamstore.json.dao;

import steamstore.json.model.DotaItem;

import java.util.List;

public interface DotaDao {

    List<DotaItem> getAll();

    DotaItem getById(long id);

    DotaItem create(String name, String quality, double cost, String rarity, String hero, String itemType);

    boolean delete(long id);

    int update(long id, String name, String quality, double cost, String rarity, String hero, String itemType);

    List<DotaItem> filter(String name, double minCost, double maxCost, String quality, String rarity, String hero, String itemType);


}