package steamstore.json.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.Objects;

public class DotaItem extends Item {
    protected final String rarity;
    // protected final DotaRarity rarity;
    protected final String hero;
    protected final String itemType;

    @JsonCreator
    public DotaItem(long id, String name, String quality, double cost, String rarity, String hero, String itemType) {
        super(id, name, quality, cost);
        this.rarity = rarity;
        this.hero = hero;
        this.itemType = itemType;
    }

    public DotaItem(long id, DotaItem item) {
        super(id, item);
        this.rarity = item.rarity;
        this.hero = item.hero;
        this.itemType = item.itemType;
    }

    @JsonGetter("rarity")
    public String getRarity() {
        return rarity;
    }

//    public DotaRarity getRarity() {
//        return rarity;
//    }

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
//                ", game=" + game +
                ", name='" + name + '\'' +
                ", rarity='" + rarity + '\'' +
                ", quality='" + quality + '\'' +
                ", cost=" + cost +
                '}';
    }
}
