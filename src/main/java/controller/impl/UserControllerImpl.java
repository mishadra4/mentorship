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

    public void extendTokenExpirationDate(User user) {
        userService.extendTokenExpirationDate(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    @Override
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    public void expireToken(User user){
        userService.expireToken(user);
    }

}
