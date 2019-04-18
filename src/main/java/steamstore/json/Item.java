package steamstore.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;

public abstract class Item {
    private int id;
    private String name;
    private String rarity;
    private String quality;
    private int count;
    private double cost;

    public Item() {
    }

    @JsonCreator
    public Item(String name, String rarity, String quality, int count, double cost) {
        this.name = name;
        this.rarity = rarity;
        this.quality = quality;
        this.count = count;
        this.cost = cost;
    }

    @JsonGetter("id")
    public int getId() {
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

    public void setId(int id) {
        this.id = id;
    }
}
