package dao.impl;

import dao.ConnectionProvider;
import dao.UserDao;
import model.Book;
import model.User;

import java.sql.*;
import java.time.LocalDateTime;

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
            statement.setString(3, user.getTokenId());
            statement.setTimestamp(4, Timestamp.valueOf(user.getTokenExpires()));
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getTokenId());
            statement.setTimestamp(3, Timestamp.valueOf(user.getTokenExpires()));
            statement.setString(4, user.getUsername());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        try {
            statement = connection.prepareStatement(FIND_USER_BY_ID_QUERY);
            statement.setString(1, username);
            rs = statement.executeQuery();
            user = retrieveUserModel(rs);
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
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

    private User retrieveUserModel(ResultSet resultSet){
        User user = new User();
        try {
            resultSet.next();
            user.setUsername(resultSet.getString(USER_USERNAME));
            user.setPassword(resultSet.getString(USER_PASSWORD));
            user.setTokenId(resultSet.getString(USER_TOKEN));
            user.setTokenExpires(resultSet.getTimestamp(USER_TOKEN_EXPIRATION).toLocalDateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

