package steamstore.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.Objects;

public abstract class Item {

    protected final long id;
    protected final String name;
    protected final String rarity;
    protected final String quality;
    protected final int count;
    protected final double cost;
//    protected long steamId;

    @JsonCreator
    public Item(long id, String name, String rarity, String quality, int count, double cost) {
        this.id = id;
        this.name = name;
        this.rarity = rarity;
        this.quality = quality;
        this.count = count;
        this.cost = cost;
    }

    @JsonGetter("id")
    public long getId() {
        return id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonGetter("count")
    public int getCount() {
        return count;
    }

    @JsonGetter("rarity")
    public String getRarity() {
        return rarity;
    }

    @JsonGetter("quality")
    public String getQuality() {
        return quality;
    }

    @JsonGetter("cost")
    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                count == item.count &&
                Double.compare(item.cost, cost) == 0 &&
                Objects.equals(name, item.name) &&
                Objects.equals(rarity, item.rarity) &&
                Objects.equals(quality, item.quality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rarity, quality, count, cost);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rarity='" + rarity + '\'' +
                ", quality='" + quality + '\'' +
                ", count=" + count +
                ", cost=" + cost +
                '}';
    }
}
