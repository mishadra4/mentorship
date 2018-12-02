package service;

import model.User;

public interface UserService {

    void saveUser(User user);

    boolean isAuthorized(User user);

    boolean isRegistered(User user);

}
