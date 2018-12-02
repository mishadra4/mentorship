package controller.impl;

import controller.UserController;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserControllerImpl implements UserController {
    UserService userService = new UserServiceImpl();

    @Override
    public void saveUser(User user) {
        userService.saveUser(user);
    }

    @Override
    public boolean isAuthorized(User user) {
        return userService.isAuthorized(user);
    }

    @Override
    public boolean isRegistered(User user) {
        return userService.isRegistered(user);
    }
}
