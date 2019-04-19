package steamstore.json.csgo;

import com.fasterxml.jackson.annotation.JsonCreator;
import steamstore.json.Games;
import steamstore.json.Item;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CsGoItem extends Item {

    protected final String weapon;
    protected final String itemCategory;
    protected final String itemType;
    protected final double floatValue;

    @JsonCreator
    public CsGoItem(long id, String name, String rarity, String quality, double cost, String weapon, String itemCategory, String itemType, double floatValue) {
        super(id, Games.CsGo, name, rarity, quality, cost);
        this.weapon = weapon;
        this.itemCategory = itemCategory;
        this.itemType = itemType;
        this.floatValue = floatValue;
    }

    public CsGoItem(long id, CsGoItem item) {
        super(id, item);
        this.weapon = item.weapon;
        this.itemCategory = item.itemCategory;
        this.itemType = item.itemType;
        this.floatValue = item.floatValue;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getItemType() {
        return itemType;
    }

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
