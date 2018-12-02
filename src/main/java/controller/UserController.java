package controller;

import model.User;

public interface UserController {

    void saveUser(User user);

    boolean isAuthorized(User user);

    boolean isRegistered(User user);

}
