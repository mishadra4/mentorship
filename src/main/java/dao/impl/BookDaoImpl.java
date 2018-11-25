package dao.impl;

import dao.BookDao;
import dao.ConnectionProvider;
import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private static final String FIND_BOOK_BY_ID_QUERY = "SELECT * FROM book WHERE book.id = ?";
    private static final String FIND_BOOKS_BY_STUDENT_ID_QUERY = "SELECT * FROM library.book INNER JOIN library.reader_has_book ON book.id = book_id WHERE reader_id = ?";
    private static final String REMOVE_BOOK_BY_ID_QUERY = "DELETE FROM book WHERE book.id = ?";
    private static final String INSERT_BOOK_QUERY = "INSERT INTO book(title, pages) VALUES (?, ?)";
    private static final String UPDATE_BOOK_QUERY = "UPDATE book SET book.title = ?, book.pages = ? WHERE book.id = ?";

    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet rs = null;

    public BookDaoImpl(){
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

    public Book getBookById(int ID){
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

    public List<Book> getBooksByReaderID(int ID){
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

    public void RemoveBookById(int ID){
        try {
            statement = connection.prepareStatement(REMOVE_BOOK_BY_ID_QUERY);
            statement.setInt(1, ID);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBook(Book book){
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

    public void updateBook(Book book){
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
        while (!rs.isLast()){
            books.add(retrieveBookModel(rs));
        }
        rs.close();
        return books;
    }

    private Book retrieveBookModel(ResultSet resultSet){
        Book book = new Book();
        try {
            resultSet.next();
            book.setID(resultSet.getInt("ID"));
            book.setTitle(resultSet.getString("title"));
            book.setPages(resultSet.getInt("pages"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

}
