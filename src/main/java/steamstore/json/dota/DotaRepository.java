package steamstore.json.dota;

import java.util.List;

public interface DotaRepository {

    List<DotaItem> loadAll();

    void saveAll(List<DotaItem> mines);

}