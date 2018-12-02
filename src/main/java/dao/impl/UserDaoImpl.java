package dao.impl;

import dao.ConnectionProvider;
import dao.UserDao;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static constant.MentorshipConstants.*;

public class UserDaoImpl implements UserDao {

    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet rs = null;

    public UserDaoImpl() {
        try {
            connection = ConnectionProvider.getLibraryDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        connection.close();
    }

    @Override
    public void saveUser(User user) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_USER_QUERY);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getHashPassword(String username) {
        String hashedPassword = null;
        try {
            statement = connection.prepareStatement(FIND_USER_PASSWORD_BY_USERNAME);
            statement.setString(1, username);
            rs = statement.executeQuery();
            rs.next();
            hashedPassword = rs.getString(USER_PASSWORD);
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }
}

