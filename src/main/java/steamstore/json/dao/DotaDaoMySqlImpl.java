package steamstore.json.dao;

import steamstore.dbutil.QueryFactory;
import steamstore.json.dao.DotaDao;
import steamstore.json.model.DotaItem;
import steamstore.json.model.enums.DotaRarity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("Duplicates")
public class DotaDaoMySqlImpl implements DotaDao {


    private final QueryFactory queryFactory;

    public DotaDaoMySqlImpl(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<DotaItem> getAll() {
        //language=SQL
        String sql = "select * from public.dota2";
        return queryFactory.uncheckedQuery().query(sql, rs -> {
            List<DotaItem> dotaItems = new ArrayList<>();
            while (rs.next())
                dotaItems.add(retrieveContact(rs));
            return dotaItems;
        });

//
    }


    private DotaItem retrieveContact(ResultSet rs) throws SQLException {
        return new DotaItem(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDouble(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7)
        );
    }

    @Override
    public DotaItem getById(long id) {
        //language=MySQL
        String sql = "select * from public.dota2 where id = ?";
        return queryFactory.uncheckedQuery().query(sql, rs -> {
            if (!rs.next()) return retrieveContact(rs);
            else return retrieveContact(rs);
        },id);
    }

    @Override
    public DotaItem create(String name, String quality, double cost, String rarity, String hero, String itemType) {
        //language=MySQL
        String sql = "insert into public.dota2 (name, quality, cost, rarity , hero, itemType) values (?, ?, ? ,? , ?, ?)";
        long generatedId = queryFactory.uncheckedQuery().insert(sql,
                rs -> {
                    if (!rs.next())
                        return rs.getLong(1);
                    else return rs.getLong(1);
                },
                name, quality, cost, rarity, hero, itemType
        );
        return new DotaItem(generatedId, name, quality, cost, rarity, hero, itemType);


    }

    @Override
    public boolean delete(long id) {
        String sql = "delete from public.dota2 where id = ?";
        int count = queryFactory.uncheckedQuery().update(sql, id);
        if (count > 0)
            return true;
        else return false;
    }

    @Override
    public int update(long id, String name, String quality, double cost, String rarity, String hero, String itemType) {
        String sql = "update public.dota2 set name=? , quality = ? , cost = ? , rarity = ? , hero = ? , itemType = ? where id = ?";
        return queryFactory.uncheckedQuery().update(sql, name, quality, cost, rarity, hero, itemType, id);
    }

    @Override
    public List<DotaItem> filter(String name, double minCost, double maxCost, String quality, String rarity, String hero, String itemType) {
        Stream<DotaItem> temp = getAll().stream();
        if (!name.equals(""))
            temp = temp.filter(dotaItem -> dotaItem.getName().equalsIgnoreCase(name));
        if (maxCost >= 0 && minCost >= 0)
            temp = temp.filter(dotaItem -> dotaItem.getCost() >= minCost - 0.0001 && dotaItem.getCost() <= maxCost + 0.00001);
        if (!rarity.equals(""))
            temp = temp.filter(dotaItem -> dotaItem.getRarity().equals(rarity));
        if (!quality.equals(""))
            temp = temp.filter(dotaItem -> dotaItem.getQuality().equalsIgnoreCase(quality));
        if (!hero.equals(""))
            temp = temp.filter(dotaItem -> dotaItem.getHero().equalsIgnoreCase(hero));
        if (!itemType.equals(""))
            temp = temp.filter(dotaItem -> dotaItem.getItemType().equalsIgnoreCase(itemType));


        return temp.collect(Collectors.toList());
    }

}
