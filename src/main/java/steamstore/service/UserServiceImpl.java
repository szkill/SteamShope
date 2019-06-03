package steamstore.service;

import steamstore.json.dao.UserDao;
import steamstore.json.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public User findUserByMail(String mail) {
        return userDao.getByMail(mail);
    }


    @Override
    public List<User> getAllUsers() {
        //Почему-то по другому нормально нельзя
        List<User> allUsers = new ArrayList();
        for (User user :
                userDao.getAll()) {
            allUsers.add(user);
        }
        return allUsers;
    }

    @Override
    public User add(String name, String surname, String mail, String password) {
       return userDao.create(name, surname, mail, password);
    }
}
