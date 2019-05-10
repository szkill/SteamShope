package steamstore.json.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.Objects;

public class Item {

    protected final long id;
   // protected final Games game;
    protected final String name;
    protected final String quality;
//    protected final int count;
    protected final double cost;
//    protected long steamId;

    @JsonCreator
    public Item(long id, String name, String quality, double cost) {
        this.id = id;
       // this.game = game;
        this.name = name;
        this.quality = quality;
        this.cost = cost;
    }

    public Item(long id, Item item) {
        this.id = id;
       // this.game = item.game;
        this.name = item.name;
        this.quality = item.quality;
        this.cost = item.cost;
    }

//    @JsonGetter("game")
//    public Games getGame() {
//        return game;
//    }

    @JsonGetter("id")
    public long getId() {
        return id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

//    @JsonGetter("count")
//    public int getCount() {
//        return count;
//    }

    @JsonGetter("quality")
    public String getQuality() {
        return quality;
    }

    @JsonGetter("cost")
    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +

                ", name='" + name + '\'' +
                ", quality='" + quality + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Double.compare(item.cost, cost) == 0 &&
//                game == item.game &&
                Objects.equals(name, item.name) &&
                Objects.equals(quality, item.quality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,  name, quality, cost);
    }
}
