package constant;

public class MentorshipConstants {
    public static final String FIND_BOOK_BY_ID_QUERY = "SELECT * FROM book WHERE book.id = ?";
    public static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM user WHERE user.username = ?";
    public static final String FIND_BOOKS_BY_STUDENT_ID_QUERY = "SELECT * FROM library.book INNER JOIN library.reader_has_book ON book.id = book_id WHERE reader_id = ?";
    public static final String REMOVE_BOOK_BY_ID_QUERY = "DELETE FROM book WHERE book.id = ?";
    public static final String INSERT_BOOK_QUERY = "INSERT INTO book(title, pages) VALUES (?, ?)";
    public static final String UPDATE_BOOK_QUERY = "UPDATE book SET book.title = ?, book.pages = ? WHERE book.id = ?";
    public static final String BOOK_ID = "ID";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_PAGES = "pages";
    public static final String FIND_READER_BY_ID_QUERY = "SELECT * FROM reader WHERE reader.id = ?";
    public static final String FIND_READERS_BY_BOOK_ID_QUERY = "SELECT * FROM reader JOIN reader_has_book ON reader.id = reader_id WHERE book_id = ?";
    public static final String REMOVE_READER_BY_ID_QUERY = "DELETE FROM reader WHERE reader.id = ?";
    public static final String INSERT_READER_QUERY = "INSERT INTO reader(first_name, last_name) VALUES (?, ?)";
    public static final String UPDATE_READER_QUERY = "UPDATE reader SET reader.first_name = ?, reader.last_name = ? WHERE reader.id = ?";
    public static final String INSERT_USER_QUERY = "INSERT INTO user(username, password, token, expiration_date) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_USER_QUERY = "UPDATE user SET user.password = ?, user.token = ?, user.expiration_date = ? WHERE user.username = ?";
    public static final String FIND_USER_PASSWORD_BY_USERNAME = "SELECT user.password FROM user WHERE user.username = ?";
    public static final String READER_ID = "ID";
    public static final String READER_FIRST_NAME = "first_name";
    public static final String READER_LAST_NAME = "last_name";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_TOKEN = "token";
    public static final String SESSION_USER = "user";
    public static final String USER_TOKEN_EXPIRATION = "expiration_date";
    public static final String LOGIN_URI = "/login";
    public static final String LOGOUT_URI = "/logout";
    public static final String ALL_URI = "/*";
    public static final String SUCCESS_URI = "/success";
    public static final int FIVE_MINS = 300;
    public static final String BOOKS = "books";
    public static final Integer INTEGER_ONE = 1;
}
