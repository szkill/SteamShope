package steamstore.json.dao;

import steamstore.dbutil.QueryFactory;
import steamstore.json.dao.CsGoDao;
import steamstore.json.model.CsGoItem;
import steamstore.json.model.enums.CsRarity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SuppressWarnings("Duplicates")
public class CsGoDaoMySqlImpl implements CsGoDao {

    private final QueryFactory queryFactory;

    public CsGoDaoMySqlImpl(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<CsGoItem> getAll() {
        //language=SQL
        String sql = "select * from public.csgo";
        return queryFactory.uncheckedQuery().query(sql, rs -> {
            List<CsGoItem> csGoItems = new ArrayList<>();
            while (rs.next())
                csGoItems.add(retrieveContact(rs));
            return csGoItems;
        });

    }


    private CsGoItem retrieveContact(ResultSet rs) throws SQLException {
        return new CsGoItem(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getDouble(9)
        );
    }

    @Override
    public int update(long id, String name, String quality, double cost, String rarity, String weapon, String itemCategory, String itemType, double floatValue) {
        String sql = "update public.csgo set name=? , quality = ? , cost = ? , rarity = ? , weapon = ? , itemCategory = ?, itemType = ? , floatValue = ? where id = ?";
        return queryFactory.uncheckedQuery().update(sql, name, quality, cost, rarity, weapon, itemCategory, itemType, floatValue, id);
    }

    @Override
    public CsGoItem getById(long id) {
        String sql = "select * from public.csgo where id = 1";
        return queryFactory.uncheckedQuery().query(sql, rs -> {
            if (!rs.next()) return retrieveContact(rs);
            else return retrieveContact(rs);
        });
    }

    @Override
    public CsGoItem create(String name, String quality, double cost, String rarity, String weapon, String itemCategory, String itemType, double floatValue) {

        //language=MySQL
        String sql = "insert into public.csgo (name, quality, cost, rarity , weapon,itemCategory, itemType, floatvalue) values (?, ?, ? ,? , ?, ?, ?, ?)";
        long generatedId = queryFactory.uncheckedQuery().insert(sql,
                rs -> {
                    if (!rs.next())
                        return rs.getLong(1);
                    else return rs.getLong(1);
                },
                name, quality, cost, rarity, weapon, itemCategory, itemType, floatValue
        );
        return new CsGoItem(generatedId, name, quality, cost, rarity, weapon, itemCategory, itemType, floatValue);

    }

    @Override
    public boolean delete(long id) {
        String sql = "delete from public.csgo where id = ?";
        int count = queryFactory.uncheckedQuery().update(sql, id);
        if (count > 0)
            return true;
        else return false;
    }


    @Override
    public List<CsGoItem> filter(String name, double minCost, double maxCost, String quality, String rarity, String weapon, String itemCategory, String itemType, double floatValue) {
        Stream<CsGoItem> temp = getAll().stream();
        if (!name.equals(""))
            temp = temp.filter(csGoItem -> csGoItem.getName().equalsIgnoreCase(name));
        if (maxCost >= 0 && minCost >= 0)
            temp = temp.filter(csGoItem -> csGoItem.getCost() >= minCost - 0.0001 && csGoItem.getCost() <= maxCost + 0.0001);
        if (!rarity.equals(CsRarity.Any.toString()))
            temp = temp.filter(csGoItem -> csGoItem.getRarity().equals(rarity));
        if (!quality.equals(""))
            temp = temp.filter(csGoItem -> csGoItem.getQuality().equalsIgnoreCase(quality));
        if (!weapon.equals(""))
            temp = temp.filter(csGoItem -> csGoItem.getWeapon().equalsIgnoreCase(weapon));
        if (!itemCategory.equals(""))
            temp = temp.filter(csGoItem -> csGoItem.getItemCategory().equalsIgnoreCase(itemCategory));
        if (!itemType.equals(""))
            temp = temp.filter(csGoItem -> csGoItem.getItemType().equalsIgnoreCase(itemType));
        if (floatValue > 0)
            temp = temp.filter(csGoItem -> Math.abs(csGoItem.getFloatValue() - floatValue) <= 0.00001);


//        List<CsGoItem> result = temp.collect(Collectors.toList());
//        if (result.size() == 0) {
//            System.out.println("Список пуст");
//        }

        return temp.collect(Collectors.toList());
    }


}

