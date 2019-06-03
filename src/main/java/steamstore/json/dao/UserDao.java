package steamstore.json.dao;

import steamstore.json.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    User getByMail(String mail);

    User create(String name, String surname, String mail, String password);

}
