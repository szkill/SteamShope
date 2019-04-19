package steamstore.json.dota;

import java.util.List;

public interface DotaDao {

    List<DotaItem> loadAll();

    void saveAll(List<DotaItem> mines);

}