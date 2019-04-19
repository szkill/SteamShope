package steamstore.json.dota;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import steamstore.json.Games;
import steamstore.json.Item;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DotaItem extends Item {

    protected final String hero;
    protected final String itemType;

    @JsonCreator
    public DotaItem(long id, String name, String rarity, String quality, double cost, String hero, String itemType) {
        super(id, Games.Dota, name, rarity, quality, cost);
        this.hero = hero;
        this.itemType = itemType;
    }

    public DotaItem(long id, DotaItem item) {
        super(id, item);
        this.hero = item.hero;
        this.itemType = item.itemType;
    }

    @JsonGetter("hero")
    public String getHero() {
        return hero;
    }

    @JsonGetter("itemType")
    public String getItemType() {
        return itemType;
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
                ", game=" + game +
                ", name='" + name + '\'' +
                ", rarity='" + rarity + '\'' +
                ", quality='" + quality + '\'' +
                ", cost=" + cost +
                '}';
    }
}
