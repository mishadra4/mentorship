package dao;

import model.User;

public interface UserDao {

    void saveUser(User user);

    String getHashPassword(String username);

}
