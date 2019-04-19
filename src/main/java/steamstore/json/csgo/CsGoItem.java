package steamstore.json.csgo;

import com.fasterxml.jackson.annotation.JsonCreator;
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
    public CsGoItem(long id, String name, String rarity, String quality, int count, double cost, String weapon, String itemCategory, String itemType, double floatValue) {
        super(id, name, rarity, quality, count, cost);
        this.weapon = weapon;
        this.itemCategory = itemCategory;
        this.itemType = itemType;
        this.floatValue = floatValue;
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
                ", name='" + name + '\'' +
                ", rarity='" + rarity + '\'' +
                ", quality='" + quality + '\'' +
                ", cost=" + cost +
                '}';
    }
}
