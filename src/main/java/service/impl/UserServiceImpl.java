package service.impl;

import com.mysql.cj.util.StringUtils;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import exception.AlreadyRegisteredException;
import exception.UnauthorizedPrincipalException;
import model.User;
import service.UserService;
import service.util.HashUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
        String hashedPassword = userDao.getHashPassword(user.getUsername());
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

    private void hashPassword(User user) {
        user.setPassword(HashUtils.hash(user.getPassword()));
    }
}
