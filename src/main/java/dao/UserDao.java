package dao;

import model.User;

public interface UserDao {

    void saveUser(User user);

    String getHashPassword(String username);

    User getUserByUsername(String username);

    void updateUser(User user);
}
