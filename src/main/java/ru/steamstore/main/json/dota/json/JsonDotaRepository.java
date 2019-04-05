package ru.steamstore.main.json.dota.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonDotaRepository implements DotaRepository {

    private final File file;
    private final ObjectMapper objectMapper;

    public JsonDotaRepository(File file, ObjectMapper objectMapper) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (file.length() == 0) {
            try {
                List<String> s1 = new ArrayList<>();
                objectMapper.writeValue(file, s1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.file = file;
        this.objectMapper = objectMapper;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public List<Dota2> loadAll() {
        if (file.length() == 0) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<Dota2>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAll(List<Dota2> mines) {
        try {
            objectMapper.writeValue(file, mines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}