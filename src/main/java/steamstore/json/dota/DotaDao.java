package steamstore.json.dota;

import java.util.List;

public interface DotaDao {

    List<DotaItem> getAll();

    DotaItem getById(long id);

    DotaItem create(String name, String rarity, String quality, double cost, String hero, String itemType);

    boolean delete(long id);


    public List<DotaItem> filter(String name, double minCost, double maxCost, String rarity, String quality, String hero, String itemType);



    List<DotaItem> loadAll();

    void saveAll();

}