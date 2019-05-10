package steamstore.json.dao;

import steamstore.exception.DotaServiceException;
import steamstore.json.model.CsGoItem;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CsGoDaoH2Impl implements CsGoDao {

    private final DataSource dataSource;

    public CsGoDaoH2Impl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CsGoItem> getAll() {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from CsGo")
        ) {
            if (rs == null)
                throw new SQLException("Unable to load contacts");

            List<CsGoItem> csGoItems = new ArrayList<>();
            while (rs.next())
                csGoItems.add(retrieveContact(rs));
            return csGoItems;
        } catch (SQLException e) {
            throw new DotaServiceException(e);
        }
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
    public CsGoItem getById(long id) {
        return null;
    }

    @Override
    public CsGoItem create(String name, String quality, double cost, String rarity, String weapon, String itemCategory, String itemType, double floatValue) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "insert into dota2 (name, quality, cost, rarity , weapon,itemCategory, itemType,floatValue ) values (?, ?, ? ,? , ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, name);
            statement.setString(2, quality);
            statement.setDouble(3, cost);
            statement.setString(4, rarity);
            statement.setString(5, weapon);
            statement.setString(6, itemCategory);
            statement.setString(7, itemType);
            statement.setDouble(8, floatValue);
            int createdRows = statement.executeUpdate();
            if (createdRows != 1)
                throw new SQLException("Unable to create CsGo");

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    return new CsGoItem(id, name, quality, cost, rarity, weapon, itemCategory, itemType, floatValue);
                } else {
                    throw new SQLException("Creating CsGo failed, no ID obtained.");
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
    public List<CsGoItem> filter(String name, double minCost, double maxCost, String quality, String rarity, String weapon, String itemCategory, String itemType, double floatValue) {
        return null;
    }

    @Override
    public List<CsGoItem> loadAll() {
        return null;
    }

    @Override
    public void saveAll() {

    }
}