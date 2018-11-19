package dao;

import model.Book;

import java.sql.*;

public class BookDao {

    public static final String FIND_BOOK_BY_ID_QUERY = "SELECT * FROM book WHERE book.id = ?";
    public static final String REMOVE_BOOK_BY_ID_QUERY = "DELETE FROM book WHERE book.id = ?";
    public static final String INSERT_BOOK_QUERY = "INSERT INTO book(title, pages) VALUES (?, ?)";
    public static final String UPDATE_BOOK_QUERY = "UPDATE book SET book.title = ?, book.pages = ? WHERE book.id = ?";

    private Connection connection;
    private PreparedStatement statement = null;
    private ResultSet rs = null;

    public BookDao(){
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

    public Book getBookforId(int ID){
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
