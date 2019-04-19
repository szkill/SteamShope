package steamstore.json.dota;

import com.fasterxml.jackson.annotation.JsonCreator;
import steamstore.json.Item;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DotaItem extends Item {

    protected final String hero;
    protected final String itemType;

    @JsonCreator
    public DotaItem(long id, String name, String rarity, String quality, int count, double cost, String hero, String itemType) {
        super(id, name, rarity, quality, count, cost);
        this.hero = hero;
        this.itemType = itemType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DotaItem item = (DotaItem) o;
        return Objects.equals(hero, item.hero) &&
                Objects.equals(itemType, item.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hero, itemType);
    }

    @Override
    public String toString() {
        return "DotaItem{" +
                "hero='" + hero + '\'' +
                ", itemType='" + itemType + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", rarity='" + rarity + '\'' +
                ", quality='" + quality + '\'' +
                ", count=" + count +
                ", cost=" + cost +
                '}';
    }
}
