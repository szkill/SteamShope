package steamstore.json.dao;

import steamstore.exception.DotaServiceException;
import steamstore.json.model.DotaItem;
import steamstore.json.model.enums.DotaRarity;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("Duplicates")
public class DotaDaoH2Impl implements DotaDao {


    private final DataSource dataSource;

    public DotaDaoH2Impl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<DotaItem> getAll() {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from dota2")
        ) {
            if (rs == null)
                throw new SQLException("Unable to load contacts");

            List<DotaItem> dotaItems = new ArrayList<>();
            while (rs.next())
                dotaItems.add(retrieveContact(rs));
            return dotaItems;
        } catch (SQLException e) {
            throw new DotaServiceException(e);
        }
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
        return null;
    }

    @Override
    public DotaItem create(String name, String quality, double cost, String rarity, String hero, String itemType) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "insert into dota2 (name, quality, cost, rarity , hero, itemType) values (?, ?, ? ,? , ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, name);
            statement.setString(2, quality);
            statement.setDouble(3, cost);
            statement.setString(4, rarity);
            statement.setString(5, hero);
            statement.setString(6, itemType);
            int createdRows = statement.executeUpdate();
            if (createdRows != 1)
                throw new SQLException("Unable to create dota2");

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    return new DotaItem(id, name, quality, cost, rarity, hero, itemType);
                } else {
                    throw new SQLException("Creating contact failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DotaServiceException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<DotaItem> filter(String name, double minCost, double maxCost, String quality, String rarity, String hero, String itemType) {
        Stream<DotaItem> temp = getAll().stream();
        if (!name.equals(""))
            temp = temp.filter(dotaItem -> dotaItem.getName().equalsIgnoreCase(name));
        if (maxCost >= 0 && minCost >= 0)
            temp = temp.filter(dotaItem -> dotaItem.getCost() >= minCost - 0.0001 && dotaItem.getCost() <= maxCost + 0.00001);
        if (rarity != DotaRarity.Any.toString())
            temp = temp.filter(dotaItem -> dotaItem.getRarity() == rarity);
        if (!quality.equals(""))
            temp = temp.filter(dotaItem -> dotaItem.getQuality().equalsIgnoreCase(quality));
        if (!hero.equals(""))
            temp = temp.filter(dotaItem -> dotaItem.getHero().equalsIgnoreCase(hero));
        if (!itemType.equals(""))
            temp = temp.filter(dotaItem -> dotaItem.getItemType().equalsIgnoreCase(itemType));

//        List<DotaItem> result = temp.collect(Collectors.toList());
//        if (result.size() == 0) {
//            System.out.println("Список пуст");
//        }

        return temp.collect(Collectors.toList());
    }

    @Override
    public List<DotaItem> loadAll() {
        return null;
    }

    @Override
    public void saveAll() {

    }
}
