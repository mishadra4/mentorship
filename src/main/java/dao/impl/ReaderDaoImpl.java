package dao.impl;

import dao.ConnectionProvider;
import dao.ReaderDao;
import model.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReaderDaoImpl implements ReaderDao {

    private static final String FIND_READER_BY_ID_QUERY = "SELECT * FROM reader WHERE reader.id = ?";
    private static final String FIND_READERS_BY_BOOK_ID_QUERY = "SELECT * FROM reader JOIN reader_has_book ON reader.id = reader_id WHERE book_id = ?";
    private static final String REMOVE_READER_BY_ID_QUERY = "DELETE FROM reader WHERE reader.id = ?";
    private static final String INSERT_READER_QUERY = "INSERT INTO reader(first_name, last_name) VALUES (?, ?)";
    private static final String UPDATE_READER_QUERY = "UPDATE reader SET reader.first_name = ?, reader.last_name = ? WHERE reader.id = ?";

    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet rs = null;

    public ReaderDaoImpl() {
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

    public Reader getReaderById(int ID) {
        Reader reader = null;
        try {
            statement = connection.prepareStatement(FIND_READER_BY_ID_QUERY);
            statement.setInt(1, ID);
            rs = statement.executeQuery();
            reader = retrieveReaderModel(rs);
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reader;
    }

    public List<Reader> getReadersByReaderID(int ID) {
        List<Reader> readers = Collections.emptyList();
        try {
            statement = connection.prepareStatement(FIND_READERS_BY_BOOK_ID_QUERY);
            statement.setInt(1, ID);
            rs = statement.executeQuery();
            readers = retrieveReaders(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readers;
    }

    public void RemoveReaderById(int ID) {
        try {
            statement = connection.prepareStatement(REMOVE_READER_BY_ID_QUERY);
            statement.setInt(1, ID);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertReader(Reader reader) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_READER_QUERY);
            statement.setString(1, reader.getFirstName());
            statement.setString(2, reader.getLastName());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReader(Reader reader) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_READER_QUERY);
            statement.setString(1, reader.getFirstName());
            statement.setString(2, reader.getLastName());
            statement.setInt(3, reader.getID());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Reader> retrieveReaders(ResultSet rs) throws SQLException {
        List<Reader> readers = new ArrayList<>();
        while (!rs.isLast()) {
            readers.add(retrieveReaderModel(rs));
        }
        rs.close();
        return readers;
    }

    private Reader retrieveReaderModel(ResultSet resultSet) {
        Reader reader = new Reader();
        try {
            resultSet.next();
            reader.setID(resultSet.getInt("ID"));
            reader.setFirstName(resultSet.getString("first_name"));
            reader.setLastName(resultSet.getString("last_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reader;
    }

}

