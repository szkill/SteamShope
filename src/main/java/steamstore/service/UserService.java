package steamstore.service;

import steamstore.json.model.User;

import java.util.List;

public interface UserService {

    String SERVICE_NAME = "UserService";

    User add(String name, String surname, String mail, String password);

    List<User> getAllUsers();

    public User findUserByMail(String name);


}
