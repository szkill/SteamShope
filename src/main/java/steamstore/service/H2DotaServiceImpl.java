package steamstore.service;

import steamstore.json.dao.DotaDao;
import steamstore.json.model.DotaItem;

import java.util.List;

public class H2DotaServiceImpl {
    private final DotaDao dotaDao;


    public H2DotaServiceImpl(DotaDao dotaDao) {
        this.dotaDao = dotaDao;
    }

    public List<DotaItem> getAll() {

        return dotaDao.getAll();
    }

    public DotaItem createDotaItem(String name, String quality, double cost, String rarity, String hero, String itemType) {
        return dotaDao.create(name, quality, cost, rarity, hero, itemType);
    }
}
