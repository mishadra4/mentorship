package service.impl;

import com.mysql.cj.util.StringUtils;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import exception.AlreadyRegisteredException;
import exception.UnauthorizedPrincipalException;
import model.User;
import service.UserService;
import service.util.HashUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static constant.MentorshipConstants.INTEGER_ONE;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    private List<String> mutableHash = new ArrayList<>();

    private Function<String, Boolean> update = hash -> {
        mutableHash.stream().findFirst().ifPresent(hash::equals);
        return true;
    };

    @Override
    public void saveUser(User user) {
        if (isRegistered(user)) {
            throw new AlreadyRegisteredException();
        }
        hashPassword(user);
        userDao.saveUser(user);
    }

    @Override
    public boolean isAuthorized(User user) {
        String hashedPassword = userDao.getHashPassword(user.getPassword());
        boolean isAuthorized = !StringUtils.isNullOrEmpty(hashedPassword) && HashUtils.verifyAndUpdateHash(user.getPassword(), hashedPassword, update);
        if (!isAuthorized) {
            throw new UnauthorizedPrincipalException();
        }
        return isAuthorized;
    }

    @Override
    public boolean isRegistered(User user) {
        return !StringUtils.isNullOrEmpty(userDao.getHashPassword(user.getUsername()));
    }

    public void extendTokenExpirationDate(User user){
        user.setTokenExpires(LocalDateTime.now().plusMinutes(INTEGER_ONE));
        updateUser(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public void updateUser(User user){
        userDao.updateUser(user);
    }

    @Override
    public void expireToken(User user) {
        user.setTokenExpires(LocalDateTime.now());
        userDao.updateUser(user);
    }

    private void hashPassword(User user) {
        user.setPassword(HashUtils.hash(user.getPassword()));
    }
}
