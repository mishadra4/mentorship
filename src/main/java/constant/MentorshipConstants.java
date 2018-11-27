package constant;

public class MentorshipConstants {
    public static final String FIND_BOOK_BY_ID_QUERY = "SELECT * FROM book WHERE book.id = ?";
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
    public static final String READER_ID = "ID";
    public static final String READER_FIRST_NAME = "first_name";
    public static final String READER_LAST_NAME = "last_name";
}
