package controller;

import model.User;

public interface UserController {

    void saveUser(User user);

    boolean isAuthorized(User user);

    boolean isRegistered(User user);

    void extendTokenExpirationDate(User user);

    User getUserByUsername(String username);

    void updateUser(User user);

    void expireToken(User user);
}
