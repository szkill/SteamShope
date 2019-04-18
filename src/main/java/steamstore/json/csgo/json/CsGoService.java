package steamstore.json.csgo.json;

import java.util.List;
import java.util.Optional;

public class CsGoService {


    private List<CsGo> items;


    public CsGoService(CsGoRepository csGoRepository) {
        items = csGoRepository.loadAll();
    }

    @SuppressWarnings("Duplicates")
    public void addItem(CsGo csGo) {
        Optional<CsGo> first = items.stream().filter(csGo1 -> csGo1.getName().equalsIgnoreCase(csGo.getName())).findFirst();
        if (!first.isPresent()) {
            this.items.add(csGo);
            return;
        }
        System.out.println("Предмет с таким именем уже есть");
    }

    public Optional<CsGo> getItemById(int id) {
        Optional<CsGo> first = items.stream().filter(csGo -> csGo.getId() == id).findFirst();
        return first;
    }

    public Optional<CsGo> getItemByName(String name) {
        Optional<CsGo> first = items.stream().filter(csGo -> csGo.getName().equalsIgnoreCase(name)).findFirst();
        return first;
    }
    public boolean removeItemById(int id) {
        return items.removeIf(csGo -> csGo.getId() == id);
    }

    public List<CsGo> getAllItems() {
        return items;
    }

    public CsGo getI(int i) {
        return items.get(i);
    }

}