package steamstore.json.csgo;

import java.util.List;

public interface CsGoDao {

    List<CsGoItem> loadAll();

    void saveAll(List<CsGoItem> mines);

}