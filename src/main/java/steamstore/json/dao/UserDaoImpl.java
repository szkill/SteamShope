package steamstore.json.dao;

import steamstore.dbutil.QueryFactory;
import steamstore.json.model.DotaItem;
import steamstore.json.model.User;
import steamstore.json.model.enums.DotaRarity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDaoImpl implements UserDao {
    private final QueryFactory queryFactory;

    public UserDaoImpl(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<User> getAll() {
        //language=MySQL
        String sql = "select * from public.users";
        return queryFactory.uncheckedQuery().query(sql, rs -> {
            List<User> users = new ArrayList<>();
            while (rs.next())
                users.add(retrieveContact(rs));
            return users;
        });

    }

    private User retrieveContact(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5)
        );
    }

//    @Override
//    public User getByMail(String mail) {
//        //language=MySQL
//        String sql = "select * from public.users where mail = ?";
//        return queryFactory.uncheckedQuery().query(sql, rs -> {
//            if (!rs.next()) return retrieveContact(rs);
//            else return retrieveContact(rs);
//        }, mail);
//    }


    @Override
    public User getByMail(String mail) {

        Supplier<Stream<User>> temp = () -> getAll().stream();
        // Stream<User> temp = getAll().stream();
        if (!temp.get().findFirst().isPresent()) {
            return null;
        }

        if (!temp.get().filter(user -> user.getMail().toLowerCase().equals(mail.toLowerCase())).findFirst().isPresent()) {
            return null;
        }
        return temp.get().filter(user -> user.getMail().toLowerCase().equals(mail.toLowerCase())).findFirst().get();
    }

    @Override
    public User create(String name, String surname, String mail, String password) {
        //language=MySQL

        String sql = "insert into public.users (name, surname,mail, password) values (?, ?, ? ,?)";
        long generatedId = queryFactory.uncheckedQuery().insert(sql,
                rs -> {
                    if (!rs.next())
                        return rs.getLong(1);
                    else return rs.getLong(1);
                },
                name, surname, mail, password
        );
        return new User(generatedId, name, surname, mail, password);
    }

    @Override
    public List<User> filter(String login) {
        return null;
    }


    @Override
    public boolean isAdmin(String mail) {

        //language=MySQL
        String sql = "select mail from public.admins where mail = ? UNION  ALL SELECT NULL Limit 1";


        //String sql = "select mail from public.admins where mail = ?";

        String t = queryFactory.uncheckedQuery().query(sql, rs -> {
            if (!rs.next()) return rs.getString("mail");
            else return rs.getString("mail");
        }, mail);

        if(t == null){
            return false;
        }
        return !t.equals("null");


    }
}
