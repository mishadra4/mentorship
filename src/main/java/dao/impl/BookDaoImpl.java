package dao.impl;

import dao.BookDao;
import dao.ConnectionProvider;
import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constant.MentorshipConstants.*;

public class BookDaoImpl implements BookDao {

    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet rs = null;

    public BookDaoImpl() {
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

    public Book getBookById(int ID) {
        Book book = null;
        try {
            statement = connection.prepareStatement(FIND_BOOK_BY_ID_QUERY);
            statement.setInt(1, ID);
            rs = statement.executeQuery();
            book = retrieveBookModel(rs);
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Book> getBooksByReaderID(int ID) {
        List<Book> books = Collections.emptyList();
        try {
            statement = connection.prepareStatement(FIND_BOOKS_BY_STUDENT_ID_QUERY);
            statement.setInt(1, ID);
            rs = statement.executeQuery();
            books = retrieveBooks(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void RemoveBookById(int ID) {
        try {
            statement = connection.prepareStatement(REMOVE_BOOK_BY_ID_QUERY);
            statement.setInt(1, ID);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBook(Book book) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_BOOK_QUERY);
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getPages());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_BOOK_QUERY);
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getPages());
            statement.setInt(3, book.getID());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Book> retrieveBooks(ResultSet rs) throws SQLException {
        List<Book> books = new ArrayList<>();
        while (!rs.isLast()) {
            books.add(retrieveBookModel(rs));
        }
        rs.close();
        return books;
    }

    private Book retrieveBookModel(ResultSet resultSet) {
        Book book = new Book();
        try {
            resultSet.next();
            book.setID(resultSet.getInt(BOOK_ID));
            book.setTitle(resultSet.getString(BOOK_TITLE));
            book.setPages(resultSet.getInt(BOOK_PAGES));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

}
