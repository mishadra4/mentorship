package dao;

import model.Book;

import java.util.List;

public interface BookDao {
    Book getBookById(int ID);

    List<Book> getBooksByReaderID(int ID);

    void RemoveBookById(int ID);

    void insertBook(Book book);

    void updateBook(Book book);

}
