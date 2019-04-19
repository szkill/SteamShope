package steamstore.json.dota;

import steamstore.json.Games;

import java.util.List;

public interface DotaDao {

    List<DotaItem> getAll();

    DotaItem getById(long id);

    DotaItem create(String name, String rarity, String quality, double cost, String hero, String itemType);

    boolean delete(long id);


    public List<DotaItem> filter(String name, double minCost, double maxCost, String hero, String itemType, String rarity, String quality);



    List<DotaItem> loadAll();

    void saveAll();

}