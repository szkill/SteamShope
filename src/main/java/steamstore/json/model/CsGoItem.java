package steamstore.json.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import steamstore.json.model.enums.CsRarity;
import steamstore.json.model.enums.Games;

import java.util.Objects;

public class CsGoItem extends Item {

    protected final CsRarity rarity;
    protected final String weapon;
    protected final String itemCategory;
    protected final String itemType;
    protected final double floatValue;

    @JsonCreator
    public CsGoItem(long id, String name, String quality, double cost, CsRarity rarity, String weapon, String itemCategory, String itemType, double floatValue) {
        super(id, Games.CsGo, name, quality, cost);
        this.rarity = rarity;
        this.weapon = weapon;
        this.itemCategory = itemCategory;
        this.itemType = itemType;
        this.floatValue = floatValue;
    }

    public CsGoItem(long id, CsGoItem item) {
        super(id, item);
        this.rarity = item.rarity;
        this.weapon = item.weapon;
        this.itemCategory = item.itemCategory;
        this.itemType = item.itemType;
        this.floatValue = item.floatValue;
    }


    @JsonGetter("rarity")
    public CsRarity getRarity() {
        return rarity;
    }

    @JsonGetter("weapon")
    public String getWeapon() {
        return weapon;
    }

    @JsonGetter("itemCategory")
    public String getItemCategory() {
        return itemCategory;
    }

    @JsonGetter("itemType")
    public String getItemType() {
        return itemType;
    }

    @JsonGetter("floatValue")
    public double getFloatValue() {
        return floatValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CsGoItem csGoItem = (CsGoItem) o;
        return Double.compare(csGoItem.floatValue, floatValue) == 0 &&
                Objects.equals(weapon, csGoItem.weapon) &&
                Objects.equals(itemCategory, csGoItem.itemCategory) &&
                Objects.equals(itemType, csGoItem.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weapon, itemCategory, itemType, floatValue);
    }

    @Override
    public String toString() {
        return "CsGoItem{" +
                "weapon='" + weapon + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", itemType='" + itemType + '\'' +
                ", floatValue=" + floatValue +
                ", id=" + id +
                ", game=" + game +
                ", name='" + name + '\'' +
                ", rarity='" + rarity + '\'' +
                ", quality='" + quality + '\'' +
                ", cost=" + cost +
                '}';
    }
}
