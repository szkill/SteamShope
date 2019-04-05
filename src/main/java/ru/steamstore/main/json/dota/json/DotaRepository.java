package ru.steamstore.main.json.dota.json;

import java.util.List;

public interface DotaRepository {

    List<Dota2> loadAll();

    void saveAll(List<Dota2> mines);

}