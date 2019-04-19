package steamstore.json.csgo;

import java.util.List;

public interface CsGoRepository {

    List<CsGoItem> loadAll();

    void saveAll(List<CsGoItem> mines);

}